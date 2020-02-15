package br.com.alex.marvelapiapp.data.datasource.remote.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val interceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder()
            .addQueryParameter("apikey", "b9d41b5ff1c83abb7e920c00d6664935")
            .addQueryParameter("ts", "1581796922")
            .addQueryParameter("hash", "af53840e4904e4e7444b8661b020c5eb")
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

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(logging).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val marvelWebService: MarvelWebService = getRetrofit().create(MarvelWebService::class.java)
}