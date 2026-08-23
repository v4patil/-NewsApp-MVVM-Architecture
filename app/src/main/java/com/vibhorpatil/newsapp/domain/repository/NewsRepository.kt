package com.vibhorpatil.newsapp.domain.repository

import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNewsSources() : Flow<List<NewsSource>>

    fun getTopHeadLines(country: String): Flow<List<Article>>

    fun getTopHeadLinesByLanguageId(languageId: String): Flow<List<Article>>

    fun getTopHeadLinesBySourceId(sourceId: String): Flow<List<Article>>

    fun getTopHeadLinesBySearchText(searchText: String): Flow<List<Article>>
}