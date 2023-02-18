package com.github.sgeorgiev24.leaf.interactor.validator

import android.util.Patterns
import com.github.sgeorgiev24.leaf.interactor.R
import javax.inject.Inject

class ValidateEmail
@Inject
constructor() {
    operator fun invoke(
        stateEvent: ValidatorStateEvent.ValidateEmail
    ) = when {
        stateEvent.email.isEmpty() -> R.string.error_email_cannot_be_empty
        !Patterns.EMAIL_ADDRESS.matcher(stateEvent.email).matches() -> R.string.error_email_invalid
        else -> null
    }
}