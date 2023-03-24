package com.github.sgeorgiev24.leaf.cache.di

import com.github.sgeorgiev24.leaf.cache.auth.datasource.LeafUserCacheDataSource
import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class CacheLeafUserDataSource

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    @CacheLeafUserDataSource
    fun provideLeafUserCacheDataSource(): DefaultCacheDataSource<LeafUserEntity> =
        LeafUserCacheDataSource()
}