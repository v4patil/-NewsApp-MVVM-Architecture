package com.vibhorpatil.newsapp.ui.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vibhorpatil.newsapp.data.model.Article
import com.vibhorpatil.newsapp.data.model.NewsSource

@Composable
fun ItemNewsArticle(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clickable(onClick = { onClick(article.url) })
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = "Article Image",
            contentScale = ContentScale.Crop
        )

        Text(
            text = article.title,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp,
            maxLines = 2
        )

        Text(
            text = article.description,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp,
            maxLines = 3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemNewsArticlePreview() {
    ItemNewsArticle(
        article = Article(
            title = "Spider-Man: Brand New day",
            description = "Tom holland stars alongside Zendaya, Saide Sink, JAcob Batalon, Jon Bernthal and Mark Ruffalo",
            url = "Url",
            imageUrl = "ImageUrl",
            source = NewsSource(sourceName = "Marvel News")
        )
    )
}