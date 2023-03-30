package com.github.sgeorgiev24.leaf.cache.di

import com.github.sgeorgiev24.leaf.cache.auth.datasource.LeafUserCacheDataSource
import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.cache.category.datasource.CategoryCacheDataSource
import com.github.sgeorgiev24.leaf.cache.category.model.CategoryEntity
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class CacheLeafUserDataSource

@Qualifier
annotation class CacheCategoryDataSource

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    @CacheLeafUserDataSource
    fun provideLeafUserCacheDataSource(): DefaultCacheDataSource<LeafUserEntity> =
        LeafUserCacheDataSource()

    @Provides
    @Singleton
    @CacheCategoryDataSource
    fun provideCategoryCacheDataSource(): DefaultCacheDataSource<CategoryEntity> =
        CategoryCacheDataSource()
}