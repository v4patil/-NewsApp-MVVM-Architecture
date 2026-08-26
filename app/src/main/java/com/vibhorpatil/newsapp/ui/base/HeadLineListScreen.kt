package com.vibhorpatil.newsapp.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vibhorpatil.newsapp.data.model.Article

@Composable
fun HeadLineListScreen(
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
