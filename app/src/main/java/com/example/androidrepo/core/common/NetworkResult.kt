package com.example.androidrepo.core.common

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val message: String) : NetworkResult<T>()
}