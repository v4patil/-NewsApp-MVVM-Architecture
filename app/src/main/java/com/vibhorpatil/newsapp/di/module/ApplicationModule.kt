package com.vibhorpatil.newsapp.di.module

import android.content.Context
import com.vibhorpatil.newsapp.NewsApplication
import com.vibhorpatil.newsapp.data.api.NetworkService
import com.vibhorpatil.newsapp.utils.WebServiceConstant.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseURL // To distinguish between the object having same return type
    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseURL baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {

        // 1. Create logging interceptor
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // 2. Create OkHttpClient with logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // 3. Build Retrofit with OkHttpClient
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

}