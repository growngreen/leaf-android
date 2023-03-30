package com.github.sgeorgiev24.leaf.network.category

import com.github.sgeorgiev24.leaf.network.category.model.CategoryDto
import com.github.sgeorgiev24.leaf.network.util.NetworkResult

interface CategoryDataSource {
    suspend fun getCategoryIconsNames(): NetworkResult<List<String>>

    suspend fun addCategory(category: CategoryDto): NetworkResult<Unit>

    suspend fun editCategory(
        categoryId: String,
        categoryName: String,
        categoryIcon: String
    ): NetworkResult<Unit>

    suspend fun deleteCategory(categoryId: String): NetworkResult<Unit>

    suspend fun getCategories(userId: String): NetworkResult<List<CategoryDto>>
}