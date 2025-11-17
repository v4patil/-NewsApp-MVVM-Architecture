package com.vibhorpatil.newsapp.ui.search

import androidx.lifecycle.ViewModel
import com.vibhorpatil.newsapp.data.repository.TopHeadLineRepository
import com.vibhorpatil.newsapp.di.module.ActivityScope
import javax.inject.Inject

@ActivityScope
class SearchViewModel @Inject constructor(repository: TopHeadLineRepository) : ViewModel() {

}