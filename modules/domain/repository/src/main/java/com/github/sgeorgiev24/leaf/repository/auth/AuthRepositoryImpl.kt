package com.github.sgeorgiev24.leaf.repository.auth

import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.repository.auth.mapper.toDomain
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

    override suspend fun signIn(
        stateEvent: StateEvent,
        email: String,
        password: String
    ) = authDataSource
        .signIn(
            email = email,
            password = password
        )
        .toDataState(stateEvent)

    override fun getUser(stateEvent: StateEvent) =
        authDataSource
            .getUser()
            .toDataState(stateEvent) { it?.toDomain() }

    override fun signOut(stateEvent: StateEvent) =
        authDataSource
            .signOut()
            .toDataState(stateEvent)
}