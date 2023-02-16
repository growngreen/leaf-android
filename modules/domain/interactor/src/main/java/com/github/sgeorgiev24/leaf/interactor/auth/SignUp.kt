package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import javax.inject.Inject

class SignUp
@Inject
constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        stateEvent: AuthStateEvent.SignUp
    ) = authRepository.signUp(
        stateEvent = stateEvent,
        email = stateEvent.email,
        name = stateEvent.name,
        password = stateEvent.password
    )
}