package com.github.sgeorgiev24.leaf.repository.auth

import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent

interface AuthRepository {
    suspend fun signUp(
        stateEvent: StateEvent,
        email: String,
        name: String,
        password: String
    ): DataState<Unit>

    suspend fun signIn(
        stateEvent: StateEvent,
        email: String,
        password: String
    ): DataState<Unit>

    fun getUser(stateEvent: StateEvent): DataState<LeafUser?>

    fun signOut(stateEvent: StateEvent): DataState<Unit>
}