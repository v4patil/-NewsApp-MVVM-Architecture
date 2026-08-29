package com.vibhorpatil.newsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vibhorpatil.newsapp.ui.base.TopAppBar
import com.vibhorpatil.newsapp.ui.navigation.NavigationAction
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.BY_LANGUAGE
import com.vibhorpatil.newsapp.utils.AppConstant.BY_SOURCE

@Composable
fun HomeScreenRoute(
    navAction : NavigationAction
) {

    Scaffold(
        topBar = { TopAppBar({}, "News App") }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                item {
                    HomeListItem("Top HeadLines", { navAction.navigateToHeadlineScreen() })
                }
                item {
                    HomeListItem("News Source", { navAction.navigateToCriteriaScreen(BY_SOURCE) })
                }
                item {
                    HomeListItem("Countries", { navAction.navigateToCriteriaScreen(BY_COUNTRY) })
                }
                item {
                    HomeListItem("Languages", { navAction.navigateToCriteriaScreen(BY_LANGUAGE) })
                }
                item {
                    HomeListItem("Search", { navAction.navigateToSearchScreen() })
                }
            }
        }
    }
}


@Composable
private fun HomeListItem(
    value: String,
    onItemClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = { onItemClick() },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}