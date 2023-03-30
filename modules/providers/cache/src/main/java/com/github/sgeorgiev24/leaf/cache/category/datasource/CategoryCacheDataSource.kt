package com.github.sgeorgiev24.leaf.cache.category.datasource

import com.github.sgeorgiev24.leaf.base.coroutines.DefaultDispatcherProvider
import com.github.sgeorgiev24.leaf.base.coroutines.DispatcherProvider
import com.github.sgeorgiev24.leaf.cache.category.model.CategoryEntity
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import com.github.sgeorgiev24.leaf.cache.util.safeCacheCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

class CategoryCacheDataSource(
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) : DefaultCacheDataSource<CategoryEntity> {

    private val category = MutableStateFlow(CategoryEntity.default)

    override suspend fun get(value: String) =
        safeCacheCall(dispatcher.io()) {
            category.value
        }

    override suspend fun set(value: CategoryEntity) =
        safeCacheCall(dispatcher.io()) {
            category.updateAndGet { value }
        }

    override suspend fun clear() =
        safeCacheCall(dispatcher.io()) {
            category.updateAndGet { CategoryEntity.default }
        }
}