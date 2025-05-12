package com.example.data.repository

import com.example.data.repository.newsDetails.NewsDetailRepository
import com.example.data.repository.newsDetails.NewsDetailRepositoryImpl
import com.example.data.repository.topnews.TopHeadlineRepository
import com.example.data.repository.topnews.TopHeadlineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTopHeadlineRepository(
        topHeadlineRepositoryImpl: TopHeadlineRepositoryImpl
    ): TopHeadlineRepository

    @Binds
    @Singleton
    abstract fun bindNewsDetailsRepository(
        newsDetailRepositoryImpl: NewsDetailRepositoryImpl
    ): NewsDetailRepository
}
