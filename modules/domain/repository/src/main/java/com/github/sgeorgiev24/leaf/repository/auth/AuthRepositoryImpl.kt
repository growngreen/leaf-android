package com.github.sgeorgiev24.leaf.repository.auth

import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.repository.extensions.toDataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl
@Inject
constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun signUp(
        stateEvent: StateEvent,
        email: String,
        name: String,
        password: String
    ) = authDataSource
        .signUp(
            email = email,
            name = name,
            password = password
        )
        .toDataState(stateEvent)
}