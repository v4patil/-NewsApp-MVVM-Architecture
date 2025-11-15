package com.vibhorpatil.newsapp.data.repository

import com.vibhorpatil.newsapp.data.api.NetworkService
import com.vibhorpatil.newsapp.data.model.NewsSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourceRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources() : Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.newsSources
        }
    }
}