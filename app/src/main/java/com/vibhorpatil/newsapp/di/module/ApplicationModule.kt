package com.vibhorpatil.newsapp.di.module

import android.content.Context
import com.vibhorpatil.newsapp.data.api.NetworkService
import com.vibhorpatil.newsapp.data.interceptor.CacheInterceptor
import com.vibhorpatil.newsapp.data.interceptor.ForceCacheInterceptor
import com.vibhorpatil.newsapp.data.repository.NewsRepositoryImpl
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import com.vibhorpatil.newsapp.utils.WebServiceConstant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

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
        @ApplicationContext context: Context,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {

        // 1. Create logging interceptor
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // 2. Create OkHttpClient with logging interceptor
        /**OkHttp is designed in such a way that it returns the cached response only when the Internet is available.

        It returns the data from the cache only when the Internet is available and the data is cached.
        It returns with the error "no internet available" even when the data is cached and but the Internet is not available.
         **/
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(context))
            .build()

        // 3. Build Retrofit with OkHttpClient
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(networkService: NetworkService) : NewsRepository {
        return NewsRepositoryImpl(networkService)
    }

}