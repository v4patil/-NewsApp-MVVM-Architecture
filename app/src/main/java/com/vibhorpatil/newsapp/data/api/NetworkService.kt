package com.vibhorpatil.newsapp.data.api

import com.vibhorpatil.newsapp.data.model.TopHeadLineResponse
import com.vibhorpatil.newsapp.utils.AppConstant.API_KEY
import com.vibhorpatil.newsapp.utils.WebServiceConstant.API_KEY_STR
import com.vibhorpatil.newsapp.utils.WebServiceConstant.GET_TOP_HEADLINE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {


    @Headers("$API_KEY_STR: $API_KEY", "User-Agent: ABC" )
    @GET(GET_TOP_HEADLINE)
    suspend fun getTopHeadLines(@Query("country") country: String) : TopHeadLineResponse

}