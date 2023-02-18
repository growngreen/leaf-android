package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import javax.inject.Inject

class SignOut
@Inject
constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        stateEvent: AuthStateEvent.SignOut
    ) = authRepository.signOut(stateEvent)
}