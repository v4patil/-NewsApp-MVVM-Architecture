package com.vibhorpatil.newsapp.ui.topheadline

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY_ID
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadLineViewmodel @Inject constructor(
    private val repository: NewsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filterBy: Int = savedStateHandle[FILTER_BY] ?: 0
    private val filterById: String = savedStateHandle[FILTER_BY_ID] ?: ""

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        when (filterBy) {
            BY_SOURCE -> {
                getTopHeadLinesBySource(filterById)
            }

            BY_LANGUAGE -> {
                getTopHeadLinesByLanguage(filterById)
            }

            else -> {
                getTopHeadLines(filterById)
            }
        }
    }

    private fun getTopHeadLines(countryCode: String) {
        viewModelScope.launch {
            repository.getTopHeadLines(countryCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun getTopHeadLinesByLanguage(languageId: String) {
        viewModelScope.launch {
            repository.getTopHeadLinesByLanguageId(languageId)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun getTopHeadLinesBySource(sourceId: String) {
        viewModelScope.launch {
            repository.getTopHeadLinesBySourceId(sourceId)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


}