package com.github.sgeorgiev24.leaf.interactor.category.cache

import com.github.sgeorgiev24.leaf.interactor.category.CategoryStateEvent
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import javax.inject.Inject

class GetCachedCategory
@Inject
constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.GetCachedCategory
    ) = categoryRepository.getCachedCategory(stateEvent)
}