package com.github.sgeorgiev24.leaf.cache.util

sealed class CacheResult<out T> {

    data class Success<out T>(val data: T) : CacheResult<T>()

    sealed class Error(val errorMessage: String) : CacheResult<Nothing>() {
        class Generic(errorMessage: String) : Error(errorMessage)
        class Timeout(errorMessage: String) : Error(errorMessage)
    }
}
