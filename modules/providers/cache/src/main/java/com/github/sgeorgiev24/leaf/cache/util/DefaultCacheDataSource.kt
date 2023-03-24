package com.github.sgeorgiev24.leaf.cache.util

import kotlinx.coroutines.flow.Flow

interface DefaultCacheDataSource<T> {

    suspend fun set(value: T): CacheResult<T> = notImplemented
    suspend fun get(value: String = ""): CacheResult<T> = notImplemented

    suspend fun satAll(list: List<T>): CacheResult<List<T>> = notImplemented
    suspend fun getAll(): CacheResult<List<T>> = notImplemented

    suspend fun asFlow(): CacheResult<Flow<T>> = notImplemented

    suspend fun clear(): CacheResult<T> = notImplemented
}

private val notImplemented = CacheResult.Error.Generic("Not implemented")