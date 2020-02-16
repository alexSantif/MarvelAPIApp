package br.com.alex.marvelapiapp.data.datasource.remote.network

import br.com.alex.marvelapiapp.utils.Constants
import br.com.alex.marvelapiapp.utils.md5
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
            .addQueryParameter("apikey", Constants.APIKEY)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", "${timestamp}f3591dfde9b9d7c6fcd4b93b9e7f0444481479ceb9d41b5ff1c83abb7e920c00d6664935".md5())
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

    val marvelWebService: MarvelWebService = getRetrofit().create(MarvelWebService::class.java)
}