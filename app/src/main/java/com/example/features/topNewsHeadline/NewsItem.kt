package com.example.features.topNewsHeadline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.R
import com.example.data.model.ArticleDTO
import com.example.features.base.intent.NewsIntent
import com.example.ui.theme.widget.TextTitle

@Composable
fun NewsItem(
    articles: List<ArticleDTO>,
    onItemSelected: (intent: NewsIntent) -> Unit,
) {
    LazyColumn {
        items(articles) { item ->
            RenderItem(
                article = item,
                onItemSelected,
            )
        }
    }
}

@Composable
private fun RenderItem(
    article: ArticleDTO,
    onItemSelected: (intent: NewsIntent) -> Unit,
) {
    val padding = dimensionResource(id = R.dimen.sixteen_dp)
    val cardPadding = dimensionResource(id = R.dimen.eight_dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(cardPadding)
            .clip(
                RoundedCornerShape(padding)
            )
            .clickable(onClick = {
                onItemSelected(
                    NewsIntent.onListSelected(article.source.name)
                )
            }),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.ten_dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .wrapContentHeight()
        ) {
            Column {
                TextTitle(
                    name = article.title,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold,
                    style = typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(cardPadding))
            }
        }
    }
}
