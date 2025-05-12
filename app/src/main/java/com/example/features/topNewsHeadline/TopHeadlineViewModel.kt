package com.example.features.topNewsHeadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ArticleDTO
import com.example.data.repository.topnews.TopHeadlineRepository
import com.example.data.repository.topnews.TopHeadlineRepositoryImpl
import com.example.features.base.intent.NewsIntent
import com.example.features.base.state.UiState
import com.example.util.Constants.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ArticleDTO>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ArticleDTO>>> = _uiState.asStateFlow()

    fun handleIntent(intent: NewsIntent, countryCode: String){
        viewModelScope.launch {
            when(intent){
                is NewsIntent.LoadTopNews -> {
                    (_uiState.value as? UiState.Loading)?.let {
                        loadTopNews(countryCode)
                    }
                }
                else -> {}
            }
        }
    }

    private suspend fun loadTopNews(countryCode: String) {
        topHeadlineRepository.getTopHeadlines(countryCode)
            .onStart { _uiState.value = UiState.Loading }
            .catch { exception -> _uiState.value = UiState.Error(exception.localizedMessage ?: UNKNOWN_ERROR) }
            .collect { articles -> _uiState.value = UiState.Success(articles) }
    }

}
