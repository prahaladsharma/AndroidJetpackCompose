package com.example.features.newsDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.R
import com.example.data.model.ArticleDTO
import com.example.features.base.intent.NewsDetailIntent
import com.example.features.base.state.UiState
import com.example.features.common.BaseScreen
import com.example.ui.theme.widget.ErrorContent
import com.example.ui.theme.widget.LoadingContent

@Composable
fun NewDetailsScreen(
    id: String,
    viewModel: NewsDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(NewsDetailIntent.LoadNewsDetail, id)
    }

    NewsDetailsScreenRender(
        state = state,
        onBack = onBack
    )
}

@Composable
private fun NewsDetailsScreenRender(
    state: UiState<List<ArticleDTO>>,
    onBack: () -> Unit,
) {
    BaseScreen(
        title = stringResource(R.string.news_details),
        showBackButton = true,
        onBackPressed = onBack,
    ) {
        state.let {
            when (it){
                is UiState.Loading -> LoadingContent()

                is UiState.Success -> NewsDetailScreen(article = it.data)

                is UiState.Error -> ErrorContent(message = it.message)
            }
        }
    }
}

@Composable
private fun NewsDetailScreen(article: List<ArticleDTO>) {
    article.map {
        NewsDetailItem(
            it,
        )
    }
}
