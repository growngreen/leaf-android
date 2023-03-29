package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import javax.inject.Inject

class EditCategory
@Inject
constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.EditCategory
    ) = categoryRepository.editCategory(
        stateEvent = stateEvent,
        categoryId = stateEvent.categoryId,
        categoryName = stateEvent.categoryName,
        categoryIcon = stateEvent.categoryIcon
    )
}