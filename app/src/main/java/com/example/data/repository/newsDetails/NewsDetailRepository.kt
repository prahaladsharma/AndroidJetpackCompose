package com.example.data.repository.newsDetails

import com.example.data.model.ArticleDTO
import kotlinx.coroutines.flow.Flow

interface NewsDetailRepository {
    fun getNewsDetail(id: String): Flow<List<ArticleDTO>>
}