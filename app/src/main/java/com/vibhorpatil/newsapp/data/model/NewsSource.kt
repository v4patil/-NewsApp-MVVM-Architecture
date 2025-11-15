package com.vibhorpatil.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val sourceName: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val sourceUrl: String = "",
    @SerializedName("category")
    val sourceCategory: String = "",
    @SerializedName("language")
    val sourceLanguage: String = "",
    @SerializedName("country")
    val sourceCountry: String = "",
)
