package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.buildError
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class GetAllCategories
@Inject
constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.GetAllCategories
    ): DataState<List<Category>> {
        val userId = userRepository.getCachedUser(stateEvent).data?.uid ?: return buildError(
            stateEvent = stateEvent,
            message = null
        )

        return categoryRepository.getCategories(
            stateEvent = stateEvent,
            userId = userId
        )
    }
}