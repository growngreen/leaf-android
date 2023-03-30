package com.github.sgeorgiev24.leaf.repository.di

import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.cache.category.model.CategoryEntity
import com.github.sgeorgiev24.leaf.cache.di.CacheCategoryDataSource
import com.github.sgeorgiev24.leaf.cache.di.CacheLeafUserDataSource
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.network.category.CategoryDataSource
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepositoryImpl
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepositoryImpl
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import com.github.sgeorgiev24.leaf.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataSource: AuthDataSource
    ): AuthRepository = AuthRepositoryImpl(authDataSource)

    @Provides
    @Singleton
    fun provideUserRepository(
        authDataSource: AuthDataSource,
        @CacheLeafUserDataSource
        leafUserCacheDataSource: DefaultCacheDataSource<LeafUserEntity>
    ): UserRepository = UserRepositoryImpl(authDataSource, leafUserCacheDataSource)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryDataSource: CategoryDataSource,
        @CacheCategoryDataSource
        categoryCacheDataSource: DefaultCacheDataSource<CategoryEntity>
    ): CategoryRepository =
        CategoryRepositoryImpl(
            categoryDataSource = categoryDataSource,
            categoryCacheDataSource = categoryCacheDataSource
        )
}