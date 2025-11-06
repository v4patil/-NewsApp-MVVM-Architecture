package com.vibhorpatil.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class TopHeadLineResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResult: Int = 0,
    @SerializedName("articles")
    val articles: List<Article> = ArrayList()

)
