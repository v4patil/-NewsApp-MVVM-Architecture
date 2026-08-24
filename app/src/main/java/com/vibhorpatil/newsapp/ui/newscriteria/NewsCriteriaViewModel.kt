package com.vibhorpatil.newsapp.ui.newscriteria

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vibhorpatil.newsapp.R
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import com.vibhorpatil.newsapp.domain.repository.NewsRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsCriteriaViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val newsRepositoryImpl: NewsRepository
) : ViewModel() {

    var filterBy: Int = BY_LANGUAGE

    private var _uiState = MutableStateFlow<UiState<List<NewsCriteria>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<NewsCriteria>>> = _uiState

    fun getData(filterBy: Int) {
        this.filterBy = filterBy
        when (filterBy) {
            BY_COUNTRY -> {
                viewModelScope.launch {
                    val countryList = returnCountryList()
                    countryList.catch {
                        _uiState.value = UiState.Error(it.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
                }
            }

            BY_SOURCE -> {
                viewModelScope.launch {
                    val countryList = newsRepositoryImpl.getNewsSources()
                    countryList.map { newsSourceList ->
                        newsSourceList.map {
                            NewsCriteria(it.id, it.sourceName)
                        }
                    }.catch {
                        _uiState.value = UiState.Error(it.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
                }
            }

            else -> {
                viewModelScope.launch {
                    val languageList = returnLanguageList()
                    languageList.catch {
                        _uiState.value = UiState.Error(it.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                    }
                }
            }
        }
    }

    private fun returnLanguageList(): Flow<List<NewsCriteria>> = flow {
        val json = context.resources
            .openRawResource(R.raw.languages)
            .bufferedReader()
            .use { it.readText() }
        val languageList: MutableList<NewsCriteria> =
            Gson().fromJson(json, object : TypeToken<List<NewsCriteria>>() {}.type)

        languageList.sortBy { it.value }
        emit(languageList)
    }

    private fun returnCountryList(): Flow<List<NewsCriteria>> = flow {
        val json = context.resources
            .openRawResource(R.raw.countries)
            .bufferedReader()
            .use { it.readText() }

        val countryList : MutableList<NewsCriteria> =
            Gson().fromJson(json, object : TypeToken<List<NewsCriteria>>() {}.type)

        countryList.sortBy { it.value }
        emit(countryList)
    }
}