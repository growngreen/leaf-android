package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import javax.inject.Inject

class SignIn
@Inject
constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        stateEvent: AuthStateEvent.SignIn
    ) = authRepository.signIn(
        stateEvent = stateEvent,
        email = stateEvent.email,
        password = stateEvent.password
    )
}