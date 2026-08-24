package com.vibhorpatil.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadLineViewmodel @Inject constructor(private val repository: NewsRepository) :
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