package com.vibhorpatil.newsapp.data.interceptor

import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(20, TimeUnit.SECONDS)
            .build()
        when {
            response.cacheResponse != null -> {
                Log.d("NETWORK", "Response came from CACHE")
            }

            response.networkResponse != null -> {
                Log.d("NETWORK", "Response came from NETWORK")
            }
        }
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}