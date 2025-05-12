package com.example.features.base.intent

sealed class NewsDetailIntent {
    data object LoadNewsDetail : NewsDetailIntent()
}