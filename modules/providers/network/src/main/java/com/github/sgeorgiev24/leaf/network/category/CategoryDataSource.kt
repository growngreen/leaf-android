package com.github.sgeorgiev24.leaf.network.category

import com.github.sgeorgiev24.leaf.network.category.model.CategoryDto
import com.github.sgeorgiev24.leaf.network.util.NetworkResult

interface CategoryDataSource {
    suspend fun getCategoryIconsNames(): NetworkResult<List<String>>

    suspend fun addCategory(category: CategoryDto): NetworkResult<Unit>
}