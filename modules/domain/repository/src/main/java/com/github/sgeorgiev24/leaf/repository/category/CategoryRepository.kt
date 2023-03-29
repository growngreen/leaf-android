package com.github.sgeorgiev24.leaf.repository.category

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent

interface CategoryRepository {
    suspend fun getCategoryIconsNames(stateEvent: StateEvent): DataState<List<String>>

    suspend fun getCategoryIconsUrls(
        stateEvent: StateEvent,
        names: List<String>
    ): DataState<List<String>>

    suspend fun addCategory(
        stateEvent: StateEvent,
        category: Category
    ): DataState<Unit>

    suspend fun editCategory(
        stateEvent: StateEvent,
        categoryId: String,
        categoryName: String,
        categoryIcon: String
    ): DataState<Unit>

    suspend fun deleteCategory(
        stateEvent: StateEvent,
        categoryId: String
    ): DataState<Unit>

    suspend fun getCategories(
        stateEvent: StateEvent,
        userId: String
    ): DataState<List<Category>>

    suspend fun getCachedCategory(
        stateEvent: StateEvent
    ): DataState<Category>

    suspend fun setCachedCategory(
        stateEvent: StateEvent,
        category: Category
    ): DataState<Unit>

    suspend fun clearCachedCategory(
        stateEvent: StateEvent
    ): DataState<Unit>
}