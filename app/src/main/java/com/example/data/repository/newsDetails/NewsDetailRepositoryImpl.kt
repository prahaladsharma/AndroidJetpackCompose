package com.example.data.repository.newsDetails

import com.example.data.api.NetworkApiService
import com.example.data.model.ArticleDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDetailRepositoryImpl @Inject constructor(
    private val networkApiService: NetworkApiService
): NewsDetailRepository {

    override fun getNewsDetail(id: String): Flow<List<ArticleDTO>> {
        return flow {
            val response = networkApiService.getNewsFromSources(id)
            emit(response.articles)
        }
    }

}
