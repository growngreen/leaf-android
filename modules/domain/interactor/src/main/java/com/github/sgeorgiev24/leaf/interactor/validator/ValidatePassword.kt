package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.interactor.R
import javax.inject.Inject

class ValidatePassword
@Inject
constructor() {
    operator fun invoke(
        stateEvent: ValidatorStateEvent.ValidatePassword
    ) = when {
        stateEvent.password.length < 6 -> R.string.error_password_too_short
        stateEvent.password.length > 50 -> R.string.error_password_too_long
        else -> null
    }
}