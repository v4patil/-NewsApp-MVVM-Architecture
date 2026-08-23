package com.vibhorpatil.newsapp.data.repository

import com.vibhorpatil.newsapp.data.api.NetworkService
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.model.NewsSource
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NewsRepository {

    override fun getNewsSources(): Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.newsSources
        }
    }

    override fun getTopHeadLines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadLines(country))
        }.map {
            it.articles
        }
    }

    override fun getTopHeadLinesByLanguageId(languageId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadLinesByLanguage(languageId))
        }.map {
            it.articles
        }
    }

    override fun getTopHeadLinesBySourceId(sourceId: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadLinesBySource(sourceId))
        }.map {
            it.articles
        }
    }

    override fun getTopHeadLinesBySearchText(searchText: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadLinesBySearchText(searchText))
        }.map {
            it.articles
        }
    }
}