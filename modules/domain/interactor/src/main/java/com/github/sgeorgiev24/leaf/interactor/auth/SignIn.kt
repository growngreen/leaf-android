package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import com.github.sgeorgiev24.leaf.repository.user.UserRepository
import javax.inject.Inject

class SignIn
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        stateEvent: AuthStateEvent.SignIn
    ): DataState<LeafUser?> {
        val user = authRepository.signIn(
            stateEvent = stateEvent,
            email = stateEvent.email,
            password = stateEvent.password
        )

        user.data?.let {
            userRepository.setCachedUser(
                stateEvent = stateEvent,
                user = it
            )
        }

        return user
    }
}