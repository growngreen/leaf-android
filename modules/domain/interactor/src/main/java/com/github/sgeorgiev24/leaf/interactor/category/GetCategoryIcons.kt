package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.mapToError
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import javax.inject.Inject

class GetCategoryIcons
@Inject
constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.GetCategoryIcons
    ): DataState<List<String>> {
        val names = categoryRepository.getCategoryIconsNames(stateEvent)

        return names.data?.let {
            categoryRepository.getCategoryIconsUrls(
                stateEvent = stateEvent,
                names = names.data ?: emptyList()
            )
        } ?: names.mapToError(stateEvent = stateEvent)
    }
}