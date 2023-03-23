package com.github.sgeorgiev24.leaf.repository.di

import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.network.category.CategoryDataSource
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepositoryImpl
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepositoryImpl
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
    fun provideCategoryRepository(
        categoryDataSource: CategoryDataSource
    ): CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
}