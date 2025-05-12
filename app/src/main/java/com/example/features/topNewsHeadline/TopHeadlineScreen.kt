package com.example.features.topNewsHeadline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.R
import com.example.data.model.ArticleDTO
import com.example.features.base.intent.NewsIntent
import com.example.features.base.state.UiState
import com.example.features.common.BaseScreen
import com.example.ui.theme.widget.ErrorContent
import com.example.ui.theme.widget.LoadingContent

@Composable
fun TopHeadlineScreen(
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    navigateToDetail: (id: String) -> Unit
){
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(NewsIntent.LoadTopNews, countryCode = "US")
    }

    NewsScreenRendering(
        state = state,
        onItemSelected = { newsIntent ->
            (newsIntent as? NewsIntent.onListSelected)
                ?.let { navigateToDetail(it.id) }
        }
    )

}

@Composable
private fun NewsScreenRendering(
    state: UiState<List<ArticleDTO>>,
    onItemSelected: (NewsIntent) -> Unit
) {
    BaseScreen(title = stringResource(R.string.home)) {
        when (state) {
            is UiState.Loading -> {
                LoadingContent()
            }

            is UiState.Success ->{
                NewsItem(
                    articles = state.data,
                    onItemSelected = onItemSelected,
                )
            }
            is UiState.Error -> {
                ErrorContent(message = state.message)
            }
        }

    }
}


