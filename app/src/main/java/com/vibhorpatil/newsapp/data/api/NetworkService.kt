package com.vibhorpatil.newsapp.data.api

import com.vibhorpatil.newsapp.data.model.NewsSourceResponse
import com.vibhorpatil.newsapp.data.model.TopHeadLineResponse
import com.vibhorpatil.newsapp.utils.AppConstant.API_KEY
import com.vibhorpatil.newsapp.utils.WebServiceConstant.API_KEY_STR
import com.vibhorpatil.newsapp.utils.WebServiceConstant.GET_TOP_HEADLINE
import com.vibhorpatil.newsapp.utils.WebServiceConstant.GET_EVERYTHING
import com.vibhorpatil.newsapp.utils.WebServiceConstant.GET_TOP_HEADLINE_SOURCE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {


    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC" )
    @GET(GET_TOP_HEADLINE)
    suspend fun getTopHeadLines(@Query("country") country: String) : TopHeadLineResponse

    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC")
    @GET(GET_TOP_HEADLINE)
    suspend fun getTopHeadLinesByLanguage(@Query("language") languageId: String) : TopHeadLineResponse

    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC")
    @GET(GET_TOP_HEADLINE)
    suspend fun getTopHeadLinesBySource(@Query("source") sourceId: String): TopHeadLineResponse

    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC")
    @GET(GET_EVERYTHING)
    suspend fun getTopHeadLinesBySearchText(@Query("q") str: String = "football"): TopHeadLineResponse


    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC" )
    @GET(GET_TOP_HEADLINE_SOURCE)
    suspend fun getNewsSources(): NewsSourceResponse

}