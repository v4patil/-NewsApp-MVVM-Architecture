package com.vibhorpatil.newsapp.ui.newscriteria

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.NewsCriteria
import com.vibhorpatil.newsapp.data.repository.NewsSourceRepository
import com.vibhorpatil.newsapp.ui.base.UiState
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsCriteriaViewModel @Inject constructor(
    private val newsSourceRepository: NewsSourceRepository
) :
    ViewModel() {

    var filterBy: Int = BY_LANGUAGE

    private var _uiState = MutableStateFlow<UiState<List<NewsCriteria>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<NewsCriteria>>> = _uiState

    fun getData(filterBy: Int) {
        this.filterBy = filterBy
        if (filterBy == BY_COUNTRY) {
            viewModelScope.launch {
                val countryList = createAndReturnCountryList()
                countryList.catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
            }
        } else if (filterBy == BY_SOURCE) {
            viewModelScope.launch {
                val countryList = newsSourceRepository.getNewsSources()
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
        } else {
            viewModelScope.launch {
                val languageList = createAndReturnLanguageList()
                languageList.catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
            }
        }
    }

    private fun createAndReturnLanguageList(): Flow<List<NewsCriteria>> = flow {
        val languageList = mutableListOf<NewsCriteria>().apply {
            add(NewsCriteria("ar", "Arabic"))
            add(NewsCriteria("de", "German"))
            add(NewsCriteria("en", "English"))
            add(NewsCriteria("es", "Spanish"))
            add(NewsCriteria("fr", "French"))
            add(NewsCriteria("he", "Hebrew"))
            add(NewsCriteria("it", "Italian"))
            add(NewsCriteria("nl", "Dutch"))
            add(NewsCriteria("no", "Norwegian"))
            add(NewsCriteria("pt", "Portuguese"))
            add(NewsCriteria("ru", "Russian"))
            add(NewsCriteria("sv", "Swedish"))
            add(NewsCriteria("ud", "Urdu"))
            add(NewsCriteria("zh", "Chinese"))
        }

        languageList.sortBy { it.value }
        emit(languageList)
    }

    private fun createAndReturnCountryList(): Flow<List<NewsCriteria>> = flow {
        val countryList: MutableList<NewsCriteria> = ArrayList()

        countryList.add(NewsCriteria("ar", "Argentina"))
        countryList.add(NewsCriteria("au", "Australia"))
        countryList.add(NewsCriteria("at", "Austria"))
        countryList.add(NewsCriteria("be", "Belgium"))
        countryList.add(NewsCriteria("br", "Brazil"))
        countryList.add(NewsCriteria("bg", "Bulgaria"))
        countryList.add(NewsCriteria("ca", "Canada"))
        countryList.add(NewsCriteria("cn", "China"))
        countryList.add(NewsCriteria("co", "Colombia"))
        countryList.add(NewsCriteria("cu", "Cuba"))
        countryList.add(NewsCriteria("cz", "Czech Republic"))
        countryList.add(NewsCriteria("eg", "Egypt"))
        countryList.add(NewsCriteria("fr", "France"))
        countryList.add(NewsCriteria("de", "Germany"))
        countryList.add(NewsCriteria("gr", "Greece"))
        countryList.add(NewsCriteria("hk", "Hong Kong"))
        countryList.add(NewsCriteria("hu", "Hungary"))
        countryList.add(NewsCriteria("in", "India"))
        countryList.add(NewsCriteria("id", "Indonesia"))
        countryList.add(NewsCriteria("ie", "Ireland"))
        countryList.add(NewsCriteria("il", "Israel"))
        countryList.add(NewsCriteria("it", "Italy"))
        countryList.add(NewsCriteria("jp", "Japan"))
        countryList.add(NewsCriteria("lv", "Latvia"))
        countryList.add(NewsCriteria("lt", "Lithuania"))
        countryList.add(NewsCriteria("my", "Malaysia"))
        countryList.add(NewsCriteria("mx", "Mexico"))
        countryList.add(NewsCriteria("ma", "Morocco"))
        countryList.add(NewsCriteria("nl", "Netherlands"))
        countryList.add(NewsCriteria("nz", "New Zealand"))
        countryList.add(NewsCriteria("ng", "Nigeria"))
        countryList.add(NewsCriteria("no", "Norway"))
        countryList.add(NewsCriteria("ph", "Philippines"))
        countryList.add(NewsCriteria("pl", "Poland"))
        countryList.add(NewsCriteria("pt", "Portugal"))
        countryList.add(NewsCriteria("ro", "Romania"))
        countryList.add(NewsCriteria("ru", "Russia"))
        countryList.add(NewsCriteria("sa", "Saudi Arabia"))
        countryList.add(NewsCriteria("rs", "Serbia"))
        countryList.add(NewsCriteria("sg", "Singapore"))
        countryList.add(NewsCriteria("sk", "Slovakia"))
        countryList.add(NewsCriteria("si", "Slovenia"))
        countryList.add(NewsCriteria("us", "United States"))

        countryList.sortBy { it.value }
        emit(countryList)
    }
}