package com.example.features.base.intent

sealed class NewsIntent {
    data object LoadTopNews: NewsIntent()

    data class onListSelected(val id:String): NewsIntent()
}