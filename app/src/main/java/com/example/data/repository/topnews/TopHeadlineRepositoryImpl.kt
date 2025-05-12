package com.example.data.repository.topnews

import com.example.data.api.NetworkApiService
import com.example.data.model.ArticleDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepositoryImpl @Inject constructor(
    private val networkApiService: NetworkApiService
) : TopHeadlineRepository {

    override fun getTopHeadlines(country: String): Flow<List<ArticleDTO>> {
        return flow {
            val response = networkApiService.getTopHeadlines(country)
            emit(response.articles)
        }
    }

}
