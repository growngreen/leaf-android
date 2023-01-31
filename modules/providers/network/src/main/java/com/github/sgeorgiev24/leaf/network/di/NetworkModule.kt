package com.github.sgeorgiev24.leaf.network.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    @Provides
//    @Singleton
//    fun provideAuthDataSource(
//        firebaseAuth: FirebaseAuth
//    ): AuthDataSource = AuthDataSourceImpl(firebaseAuth)
//
//    @Singleton
//    @Provides
//    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
//
//    @Singleton
//    @Provides
//    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}