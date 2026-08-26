package com.vibhorpatil.newsapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.ui.base.EmptyScreen
import com.vibhorpatil.newsapp.ui.base.ItemNewsArticle
import com.vibhorpatil.newsapp.ui.base.LoadingScreen
import com.vibhorpatil.newsapp.ui.base.TopAppBar
import com.vibhorpatil.newsapp.ui.base.UiState

@Composable
fun SearchRoute(
    onBack: () -> Unit = {},
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(onBack, uiState, {}, { viewModel.searchBy("tom Hall") })
}

@Composable
private fun SearchScreen(
    onBack: () -> Unit,
    uiState: UiState<List<Article>>,
    onItemClick: (String) -> Unit,
    onSearch: (String) -> Unit
) {

    Scaffold(
        topBar = { TopAppBar(onBack, "Search Screen") }
    ) { paddingValues ->

        when (val state = uiState) {
            is UiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
                onSearch("tomHalland")
            }

            is UiState.Error -> {}
            is UiState.Success -> {
                SearchScreenContent(Modifier.padding(paddingValues), state.data, onItemClick)
            }
        }
    }
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier,
    list: List<Article>,
    onItemClick: (String) -> Unit
) {
    if (list.isEmpty()) {
        EmptyScreen("No Data to Show", modifier)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            LazyColumn {
                items(
                    key = { it.imageUrl },
                    items = list
                ) {
                    ItemNewsArticle(modifier, it, onItemClick)
                }
            }
        }
    }
}
