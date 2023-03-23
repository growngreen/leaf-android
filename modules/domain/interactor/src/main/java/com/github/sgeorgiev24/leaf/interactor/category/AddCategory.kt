package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.buildError
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import javax.inject.Inject

class AddCategory
@Inject
constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.AddCategory
    ): DataState<Unit> {
        return categoryRepository.addCategory(
            stateEvent = stateEvent,
            category = Category(
                title = stateEvent.title,
                type = stateEvent.type ?: return buildError(
                    stateEvent = stateEvent,
                    message = null
                ),
                icon = stateEvent.icon ?: return buildError(
                    stateEvent = stateEvent,
                    message = null
                ),
            )
        )
    }
}