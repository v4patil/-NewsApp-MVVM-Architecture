package com.vibhorpatil.newsapp.ui.newscriteria

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vibhorpatil.newsapp.domain.model.NewsCriteria
import com.vibhorpatil.newsapp.ui.base.EmptyScreen
import com.vibhorpatil.newsapp.ui.base.LoadingScreen
import com.vibhorpatil.newsapp.ui.base.UiState

@Composable
fun NewsCriteriaRoute(
    onItemClick: (NewsCriteria) -> Unit,
    viewModel: NewsCriteriaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewsCriteriaScreen(uiState, onItemClick)
}

@Composable
private fun NewsCriteriaScreen(
    uiState: UiState<List<NewsCriteria>>,
    onItemClick: (NewsCriteria) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            snackBarHostState.showSnackbar(message = "An Error Occurred")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { paddingValues ->

        when (val state = uiState) {
            is UiState.Loading -> {
                LoadingScreen(modifier = Modifier.padding(paddingValues).fillMaxSize())
            }

            is UiState.Error -> {}

            is UiState.Success -> {
                NewsCriteriaScreenContent(
                    Modifier.padding(paddingValues),
                    state.data,
                    onItemClick
                )
            }
        }
    }
}

@Composable
private fun NewsCriteriaScreenContent(
    modifier: Modifier = Modifier,
    list: List<NewsCriteria>,
    onItemClick: (NewsCriteria) -> Unit
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
                    items = list,
                    key = { it.id }) {
                    CriteriaListItem(it, onItemClick)
                }
            }
        }
    }
}

@Composable
private fun CriteriaListItem(
    newsCriteria: NewsCriteria,
    onItemClick: (NewsCriteria) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onItemClick(newsCriteria) },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = newsCriteria.value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CriteriaListItemPreview() {
    CriteriaListItem(
        newsCriteria = NewsCriteria("123", "News Criteria")
    )
}

