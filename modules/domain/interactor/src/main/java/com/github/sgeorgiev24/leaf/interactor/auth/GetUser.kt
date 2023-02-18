package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import javax.inject.Inject

class GetUser
@Inject
constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        stateEvent: AuthStateEvent.GetUser
    ) = authRepository.getUser(stateEvent)
}