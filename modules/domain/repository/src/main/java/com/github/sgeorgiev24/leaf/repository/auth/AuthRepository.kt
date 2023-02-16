package com.github.sgeorgiev24.leaf.repository.auth

import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent

interface AuthRepository {
    suspend fun signUp(
        stateEvent: StateEvent,
        email: String,
        name: String,
        password: String
    ): DataState<Unit>
}