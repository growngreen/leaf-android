package com.github.sgeorgiev24.leaf.network.util

import com.github.sgeorgiev24.leaf.network.util.NetworkConst.NETWORK_TIMEOUT
import com.github.sgeorgiev24.leaf.network.util.NetworkConst.UNKNOWN_ERROR
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

private const val RESPONSE_CODE_EMPTY = 204

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> (Response<T>)
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            withTimeout(NETWORK_TIMEOUT) {
                create(apiCall())
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> {
                    NetworkResult.CancellationError
                }
                is IOException -> {
                    NetworkResult.NetworkError
                }
                is HttpException -> {
                    convertErrorBody(throwable)
                }
                else -> {
                    NetworkResult.Error(message = UNKNOWN_ERROR)
                }
            }
        }
    }
}

fun <T> create(response: Response<T>): NetworkResult<T> {
    return if (response.isSuccessful) {
        val body = response.body()
        if (body == null || response.code() == RESPONSE_CODE_EMPTY) {
            NetworkResult.Empty
        } else {
            NetworkResult.Success(body)
        }
    } else {
        val msg = response.errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) response.message() else msg
        NetworkResult.Error(message = errorMsg)
    }
}

private fun <T> convertErrorBody(throwable: HttpException): NetworkResult<T> {
    return when (throwable.code()) {
        // For some reason when token is invalid return code is 419
        419 -> NetworkResult.Unauthorized
        else -> {
            try {
                NetworkResult.Error(message = throwable.response()?.errorBody()?.string() ?: "")
            } catch (exception: Exception) {
                NetworkResult.Error(message = UNKNOWN_ERROR)
            }
        }
    }
}
