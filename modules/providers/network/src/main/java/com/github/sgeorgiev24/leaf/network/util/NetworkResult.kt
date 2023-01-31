package com.github.sgeorgiev24.leaf.network.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class NetworkResult<out T> {

    object Empty : NetworkResult<Nothing>()
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(
        val message: String? = null,
        val code: Int? = null
    ) : NetworkResult<Nothing>()

    object NetworkError : NetworkResult<Nothing>()
    object CancellationError : NetworkResult<Nothing>()
    object Unauthorized : NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Error -> "Error[exception={message=$message}]"
            NetworkError -> "Network Error"
            CancellationError -> "Cancellation Error"
            Unauthorized -> "Unauthorized Error"
            Empty -> "Success - empty response"
        }
    }
}

/**
 * `true` if [NetworkResult] is of type [Success] & holds non-null [Success.data].
 */
val <T> NetworkResult<T>.succeeded
    get() = this is NetworkResult.Success<T> && data != null

inline fun <T> NetworkResult<T>.onSuccess(
    crossinline callback: (value: T) -> Unit
): NetworkResult<T> {
    if (this is NetworkResult.Success) {
        callback((this).data)
    }
    return this
}

inline fun <T> NetworkResult<T>.onError(
    crossinline callback: (value: NetworkResult.Error) -> Unit
): NetworkResult<T> {
    if (this is NetworkResult.Error) {
        callback(this)
    }
    return this
}
