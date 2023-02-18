package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.interactor.R
import javax.inject.Inject

class ValidateConfirmPassword
@Inject
constructor() {
    operator fun invoke(
        stateEvent: ValidatorStateEvent.ValidateConfirmPassword
    ) = when {
        stateEvent.password != stateEvent.confirmPassword -> R.string.error_passwords_do_not_match
        else -> null
    }
}