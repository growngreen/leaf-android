package com.github.sgeorgiev24.leaf.network.di

import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.network.auth.AuthDataSourceImpl
import com.github.sgeorgiev24.leaf.network.category.CategoryDataSource
import com.github.sgeorgiev24.leaf.network.category.CategoryDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(
        firebaseAuth: FirebaseAuth
    ): AuthDataSource = AuthDataSourceImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideCategoryDataSource(
        storage: FirebaseStorage,
        firestore: FirebaseFirestore
    ): CategoryDataSource = CategoryDataSourceImpl(storage = storage, firestore = firestore)
}