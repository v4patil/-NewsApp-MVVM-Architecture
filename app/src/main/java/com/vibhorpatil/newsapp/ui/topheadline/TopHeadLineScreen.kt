package com.vibhorpatil.newsapp.ui.topheadline

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.ui.base.HeadLineListScreen
import com.vibhorpatil.newsapp.ui.base.LoadingScreen
import com.vibhorpatil.newsapp.ui.base.TopAppBar
import com.vibhorpatil.newsapp.ui.base.UiState

@Composable
fun TopHeadLineRoute(
    onBack: () -> Unit,
    viewModel: TopHeadLineViewmodel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TopHeadLineScreen(onBack, uiState)
}

@Composable
private fun TopHeadLineScreen(
    onBack: () -> Unit,
    uiState: UiState<List<Article>>,
    onItemClick: (String) -> Unit = {}
) {

    Scaffold(
        topBar = { TopAppBar(onBack = onBack, "Top Headline") }
    ) { paddingValues ->

        when (val state = uiState){
            is UiState.Loading -> {
                LoadingScreen(Modifier.padding(paddingValues).fillMaxSize())
            }
            is UiState.Error -> {}
            is UiState.Success -> {
                HeadLineListScreen(Modifier.padding(paddingValues), state.data, onItemClick)
            }
        }
    }

}
