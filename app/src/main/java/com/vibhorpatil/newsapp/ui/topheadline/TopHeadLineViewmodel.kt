package com.vibhorpatil.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.repository.TopHeadLineRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.utils.AppConstant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadLineViewmodel (private val repository: TopHeadLineRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun getTopHeadLines(countryCode: String) {
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