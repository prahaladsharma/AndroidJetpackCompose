package com.example.util

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(val error: String?) : Result<T>()
}