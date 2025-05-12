package com.example.data.repository.topnews

import com.example.data.model.ArticleDTO
import kotlinx.coroutines.flow.Flow

interface TopHeadlineRepository {
    fun getTopHeadlines(country: String): Flow<List<ArticleDTO>>
}
