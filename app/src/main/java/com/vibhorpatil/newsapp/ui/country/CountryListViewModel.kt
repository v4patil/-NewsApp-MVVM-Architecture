package com.vibhorpatil.newsapp.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.newsapp.data.model.Country
import com.vibhorpatil.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class CountryListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        getCountryList()
    }

    private fun getCountryList() {
        viewModelScope.launch {
            val countryList: Flow<List<Country>> = createAndReturnCountryList()
            countryList
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun createAndReturnCountryList(): Flow<List<Country>> = flow {
        val countryList: MutableList<Country> = ArrayList()

        countryList.add(Country("ar", "Argentina"))
        countryList.add(Country("au", "Australia"))
        countryList.add(Country("at", "Austria"))
        countryList.add(Country("be", "Belgium"))
        countryList.add(Country("br", "Brazil"))
        countryList.add(Country("bg", "Bulgaria"))
        countryList.add(Country("ca", "Canada"))
        countryList.add(Country("cn", "China"))
        countryList.add(Country("co", "Colombia"))
        countryList.add(Country("cu", "Cuba"))
        countryList.add(Country("cz", "Czech Republic"))
        countryList.add(Country("eg", "Egypt"))
        countryList.add(Country("fr", "France"))
        countryList.add(Country("de", "Germany"))
        countryList.add(Country("gr", "Greece"))
        countryList.add(Country("hk", "Hong Kong"))
        countryList.add(Country("hu", "Hungary"))
        countryList.add(Country("in", "India"))
        countryList.add(Country("id", "Indonesia"))
        countryList.add(Country("ie", "Ireland"))
        countryList.add(Country("il", "Israel"))
        countryList.add(Country("it", "Italy"))
        countryList.add(Country("jp", "Japan"))
        countryList.add(Country("lv", "Latvia"))
        countryList.add(Country("lt", "Lithuania"))
        countryList.add(Country("my", "Malaysia"))
        countryList.add(Country("mx", "Mexico"))
        countryList.add(Country("ma", "Morocco"))
        countryList.add(Country("nl", "Netherlands"))
        countryList.add(Country("nz", "New Zealand"))
        countryList.add(Country("ng", "Nigeria"))
        countryList.add(Country("no", "Norway"))
        countryList.add(Country("ph", "Philippines"))
        countryList.add(Country("pl", "Poland"))
        countryList.add(Country("pt", "Portugal"))
        countryList.add(Country("ro", "Romania"))
        countryList.add(Country("ru", "Russia"))
        countryList.add(Country("sa", "Saudi Arabia"))
        countryList.add(Country("rs", "Serbia"))
        countryList.add(Country("sg", "Singapore"))
        countryList.add(Country("sk", "Slovakia"))
        countryList.add(Country("si", "Slovenia"))

        countryList.sortBy { it.countryName }
        emit(countryList)
    }
}