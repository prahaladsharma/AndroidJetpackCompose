package com.example.features.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ArticleDTO
import com.example.data.repository.newsDetails.NewsDetailRepository
import com.example.data.repository.newsDetails.NewsDetailRepositoryImpl
import com.example.features.base.intent.NewsDetailIntent
import com.example.features.base.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsDetailRepository: NewsDetailRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<ArticleDTO>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ArticleDTO>>> = _uiState.asStateFlow()

    fun handleIntent(
        intent: NewsDetailIntent,
        id: String,
    ) {
        when(intent){
            is NewsDetailIntent.LoadNewsDetail -> getNewsDetail(id)

        }
    }

    private fun getNewsDetail(id: String) {
        viewModelScope.launch {
            newsDetailRepository.getNewsDetail(id)
                .catch { exc ->
                    _uiState.value = UiState.Error(exc.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}