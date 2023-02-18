package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.model.state.StateEvent

sealed class ValidatorStateEvent : StateEvent {
    data class ValidateEmail(
        val email: String
    ) : ValidatorStateEvent() {
        override fun errorInfo() = "Error while trying to validate email"
        override fun shouldDisplayProgressBar() = false
    }
}
