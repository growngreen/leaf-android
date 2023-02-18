package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.model.state.StateEvent

sealed class ValidatorStateEvent : StateEvent {
    data class ValidateEmail(
        val email: String
    ) : ValidatorStateEvent() {
        override fun errorInfo() = "Error while trying to validate email"
        override fun shouldDisplayProgressBar() = false
    }

    data class ValidatePassword(
        val password: String
    ) : ValidatorStateEvent() {
        override fun errorInfo() = "Error while trying to validate password"
        override fun shouldDisplayProgressBar() = false
    }

    data class ValidateConfirmPassword(
        val password: String,
        val confirmPassword: String
    ) : ValidatorStateEvent() {
        override fun errorInfo() = "Error while trying to validate confirm password"
        override fun shouldDisplayProgressBar() = false
    }
}
