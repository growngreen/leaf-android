package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class SignOut
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: AuthStateEvent.SignOut
    ): DataState<Unit> {
        val signOutResult = authRepository.signOut(stateEvent)

        signOutResult.data?.let {
            userRepository.clearCachedUser(stateEvent)
        }

        return signOutResult
    }
}