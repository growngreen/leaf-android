package com.github.sgeorgiev24.leaf.interactor.user

import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class GetUser
@Inject
constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: UserStateEvent.GetUser
    ): DataState<LeafUser?> {
        val user = userRepository.getUser(stateEvent)

        user.data?.let {
            userRepository.setCachedUser(
                stateEvent = stateEvent,
                user = it
            )
        }

        return user
    }
}