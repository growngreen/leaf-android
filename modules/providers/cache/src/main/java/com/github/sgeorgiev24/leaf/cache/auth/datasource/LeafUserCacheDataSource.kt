package com.github.sgeorgiev24.leaf.cache.auth.datasource

import com.github.sgeorgiev24.leaf.base.coroutines.DefaultDispatcherProvider
import com.github.sgeorgiev24.leaf.base.coroutines.DispatcherProvider
import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import com.github.sgeorgiev24.leaf.cache.util.safeCacheCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

class LeafUserCacheDataSource(
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) : DefaultCacheDataSource<LeafUserEntity> {

    private val user = MutableStateFlow(LeafUserEntity.default)

    override suspend fun get(value: String) =
        safeCacheCall(dispatcher.io()) {
            user.value
        }

    override suspend fun set(value: LeafUserEntity) =
        safeCacheCall(dispatcher.io()) {
            user.updateAndGet { value }
        }

    override suspend fun clear() =
        safeCacheCall(dispatcher.io()) {
            user.updateAndGet { LeafUserEntity.default }
        }
}