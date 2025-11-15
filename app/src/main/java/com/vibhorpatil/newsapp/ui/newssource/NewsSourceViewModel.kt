package com.vibhorpatil.newsapp.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.NewsSource
import com.vibhorpatil.newsapp.data.repository.NewsSourceRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourceViewModel (private val newsSourceRepository: NewsSourceRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<NewsSource>>>(UiState.Loading)

    val uiState : StateFlow<UiState<List<NewsSource>>> = _uiState

    init {
        getNewsSources()
    }

    private fun getNewsSources() {
        viewModelScope.launch {
            newsSourceRepository.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}