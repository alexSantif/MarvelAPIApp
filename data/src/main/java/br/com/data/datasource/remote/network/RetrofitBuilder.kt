package br.com.data.datasource.remote.network

import br.com.sharedutils.Constants
import br.com.sharedutils.md5
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object RetrofitBuilder {
    private val interceptor = Interceptor { chain ->
        val timestamp = Timestamp(System.currentTimeMillis()).toString()
        val url = chain.request().url.newBuilder()
            .addQueryParameter(
                Constants.APIKEY,
                Constants.KEY
            )
            .addQueryParameter(Constants.TIMESTAMP_KEY, timestamp)
            .addQueryParameter(
                Constants.HASH_KEY,
                "${timestamp}${Constants.HASH}".md5()
            )
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    private val logging = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val apiClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(logging).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val marvelWebService: MarvelWebService =
        getRetrofit().create(
            MarvelWebService::class.java
        )
}