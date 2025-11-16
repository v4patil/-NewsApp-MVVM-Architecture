package com.vibhorpatil.newsapp.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.Language
import com.vibhorpatil.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LanguageListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        viewModelScope.launch {
            val languageList = createAndReturnLanguageList()
            languageList
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun createAndReturnLanguageList(): Flow<List<Language>> = flow {
        val languageList = mutableListOf<Language>().apply {
            add(Language("ar", "Arabic"))
            add(Language("de", "German"))
            add(Language("en", "English"))
            add(Language("es", "Spanish"))
            add(Language("fr", "French"))
            add(Language("he", "Hebrew"))
            add(Language("it", "Italian"))
            add(Language("nl", "Dutch"))
            add(Language("no", "Norwegian"))
            add(Language("pt", "Portuguese"))
            add(Language("ru", "Russian"))
            add(Language("sv", "Swedish"))
            add(Language("ud", "Urdu"))
            add(Language("zh", "Chinese"))
        }

        languageList.sortBy { it.languageName }
        emit(languageList)
    }
}