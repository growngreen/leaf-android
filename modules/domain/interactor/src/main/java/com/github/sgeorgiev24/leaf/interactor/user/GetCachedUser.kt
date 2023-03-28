package com.github.sgeorgiev24.leaf.interactor.user

import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class GetCachedUser
@Inject
constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: UserStateEvent.GetCachedUser
    ) = userRepository.getCachedUser(stateEvent)
}