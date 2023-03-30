package com.github.sgeorgiev24.leaf.repository.category

import com.github.sgeorgiev24.leaf.cache.category.model.CategoryEntity
import com.github.sgeorgiev24.leaf.cache.di.CacheCategoryDataSource
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.model.state.buildSuccessData
import com.github.sgeorgiev24.leaf.network.category.CategoryDataSource
import com.github.sgeorgiev24.leaf.repository.Secret
import com.github.sgeorgiev24.leaf.repository.category.mapper.toDomain
import com.github.sgeorgiev24.leaf.repository.category.mapper.toDto
import com.github.sgeorgiev24.leaf.repository.category.mapper.toEntity
import com.github.sgeorgiev24.leaf.repository.extensions.toDataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl
@Inject
constructor(
    private val categoryDataSource: CategoryDataSource,
    @CacheCategoryDataSource
    private val categoryCacheDataSource: DefaultCacheDataSource<CategoryEntity>
) : CategoryRepository {

    override suspend fun getCategoryIconsNames(
        stateEvent: StateEvent
    ) = categoryDataSource
        .getCategoryIconsNames()
        .toDataState(stateEvent)

    override suspend fun getCategoryIconsUrls(
        stateEvent: StateEvent,
        names: List<String>
    ): DataState<List<String>> {
        val urls = names.map {
            Secret.getFirebaseStorageUrl(it)
        }

        return buildSuccessData(data = urls, stateEvent = stateEvent)
    }

    override suspend fun addCategory(
        stateEvent: StateEvent,
        category: Category
    ) = categoryDataSource
        .addCategory(category.toDto())
        .toDataState(stateEvent)

    override suspend fun editCategory(
        stateEvent: StateEvent,
        categoryId: String,
        categoryName: String,
        categoryIcon: String
    ) = categoryDataSource
        .editCategory(
            categoryId = categoryId,
            categoryName = categoryName,
            categoryIcon = categoryIcon
        )
        .toDataState(stateEvent)

    override suspend fun deleteCategory(
        stateEvent: StateEvent,
        categoryId: String
    ) = categoryDataSource
        .deleteCategory(categoryId)
        .toDataState(stateEvent)

    override suspend fun getCategories(
        stateEvent: StateEvent,
        userId: String
    ) = categoryDataSource
        .getCategories(userId = userId)
        .toDataState(stateEvent) {
            it.map { categoryDto -> categoryDto.toDomain() }
        }

    override suspend fun getCachedCategory(
        stateEvent: StateEvent
    ) = categoryCacheDataSource
        .get()
        .toDataState(stateEvent) { it.toDomain() }

    override suspend fun setCachedCategory(
        stateEvent: StateEvent,
        category: Category
    ) = categoryCacheDataSource
        .set(category.toEntity())
        .toDataState(stateEvent) {}

    override suspend fun clearCachedCategory(
        stateEvent: StateEvent
    ) = categoryCacheDataSource
        .clear()
        .toDataState(stateEvent) {}
}