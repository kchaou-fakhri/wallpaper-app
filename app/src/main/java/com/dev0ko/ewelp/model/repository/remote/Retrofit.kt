package com.dev0ko.ewelp.model.repository.remote


import com.dev0ko.ewelp.BuildConfig
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit;
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor




object Retrofit {

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization","Client-ID " + BuildConfig.ACCESS_KEY)
                    .build()
                chain.proceed(request)
            })
            .build()
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val api : UnsplachService by lazy {
        retrofit.create(UnsplachService::class.java)
    }
}