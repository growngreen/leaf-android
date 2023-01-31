package com.github.sgeorgiev24.leaf.presentation.di

import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationManager
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun provideNavigationManager(): NavigationManager {
        return NavigationManagerImpl()
    }
}