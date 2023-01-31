package com.github.sgeorgiev24.leaf.cache.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    timeout: Long = CacheConst.CACHE_TIMEOUT,
    cacheCall: suspend () -> T
): CacheResult<T> {
    return withContext(dispatcher) {
        try {
            withTimeout(timeout) {
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()

            when (throwable) {
                is TimeoutCancellationException -> {
                    CacheResult.Error.Timeout(CacheError.CACHE_ERROR_TIMEOUT)
                }
                else -> {
                    CacheResult.Error.Generic(CacheError.CACHE_ERROR_UNKNOWN)
                }
            }
        }
    }
}