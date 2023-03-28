package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.category.add.CategoryType
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.buildError
import com.github.sgeorgiev24.leaf.repository.category.CategoryRepository
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class AddCategory
@Inject
constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: CategoryStateEvent.AddCategory
    ): DataState<Unit> {
        val userId = userRepository.getCachedUser(stateEvent).data?.uid ?: return buildError(
            stateEvent = stateEvent,
            message = null
        )
        return categoryRepository.addCategory(
            stateEvent = stateEvent,
            category = buildCategory(userId = userId, stateEvent = stateEvent)
        )
    }

    private fun buildCategory(
        userId: String,
        stateEvent: CategoryStateEvent.AddCategory
    ) = Category(
        id = "",
        userId = userId,
        title = stateEvent.title,
        type = stateEvent.type ?: CategoryType.INCOME,
        icon = stateEvent.icon ?: ""
    )
}