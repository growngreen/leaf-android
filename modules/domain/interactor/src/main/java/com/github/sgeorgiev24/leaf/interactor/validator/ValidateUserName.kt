package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.interactor.R
import javax.inject.Inject

class ValidateUserName
@Inject
constructor() {
    operator fun invoke(
        stateEvent: ValidatorStateEvent.ValidateUserName
    ) = when {
        stateEvent.name.isEmpty() -> R.string.error_name_cannot_be_empty
        stateEvent.name.length < 2 -> R.string.error_name_too_short
        stateEvent.name.length > 30 -> R.string.error_name_too_long
        else -> null
    }
}