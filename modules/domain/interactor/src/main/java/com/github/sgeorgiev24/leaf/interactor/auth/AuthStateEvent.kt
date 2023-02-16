package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.model.state.StateEvent

sealed class AuthStateEvent : StateEvent {
    data class SignUp(
        val email: String,
        val name: String,
        val password: String
    ) : AuthStateEvent() {
        override fun errorInfo() = "Error while trying to sign up"
        override fun shouldDisplayProgressBar() = true
    }
}
