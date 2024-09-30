package com.dev0ko.ewelp.di

import com.dev0ko.ewelp.BuildConfig
import com.dev0ko.ewelp.data.repository.photos.remote.UnsplashRepository
import com.dev0ko.ewelp.data.repository.photos.remote.UnsplashService
import com.dev0ko.ewelp.domain.repository.IUnsplash
import com.dev0ko.ewelp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideUnsplash(): UnsplashService {
         val retrofit by lazy {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                        .addHeader(Constants.AUTHORIZATION, Constants.CLIENT_ID + BuildConfig.ACCESS_KEY)
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
       return retrofit.create(UnsplashService::class.java)
    }

        @Singleton
        @Provides
        fun provideUnsplashRepository(unsplashService: UnsplashService): IUnsplash =
            UnsplashRepository(unsplashService)


    }