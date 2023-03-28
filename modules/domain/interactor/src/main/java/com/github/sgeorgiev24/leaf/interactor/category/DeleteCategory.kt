package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import javax.inject.Inject

class DeleteCategory
@Inject
constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.DeleteCategory
    ) = categoryRepository.deleteCategory(
        stateEvent = stateEvent,
        categoryId = stateEvent.categoryId
    )
}