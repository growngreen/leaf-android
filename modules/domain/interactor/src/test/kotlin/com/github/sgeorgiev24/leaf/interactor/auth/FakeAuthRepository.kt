package com.github.sgeorgiev24.leaf.interactor.auth

import android.util.Patterns
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository

class FakeAuthRepository : AuthRepository {
    override suspend fun signUp(
        stateEvent: StateEvent,
        email: String,
        name: String,
        password: String
    ): DataState<Unit> {
        val emailError =
            email.isEmpty()
        val nameError =
            name.length < 2 || name.length > 30
        val passwordError =
            password.length < 6 || password.length > 50

        return if (nameError || emailError || passwordError) {
            DataState(data = null)
        } else {
            DataState(data = Unit)
        }
    }

    override suspend fun signIn(
        stateEvent: StateEvent,
        email: String,
        password: String
    ): DataState<Unit> {
        val emailError =
            email.isEmpty()
        val passwordError =
            password.length < 6 || password.length > 50

        return if (emailError || passwordError) {
            DataState(data = null)
        } else {
            DataState(data = Unit)
        }
    }
}