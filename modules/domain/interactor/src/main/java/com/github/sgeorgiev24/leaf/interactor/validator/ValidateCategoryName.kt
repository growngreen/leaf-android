package com.github.sgeorgiev24.leaf.interactor.validator

import com.github.sgeorgiev24.leaf.interactor.R
import javax.inject.Inject

class ValidateCategoryName
@Inject
constructor() {
    operator fun invoke(
        stateEvent: ValidatorStateEvent.ValidateCategoryName
    ) = when {
        stateEvent.categoryName.isEmpty() -> R.string.error_name_cannot_be_empty
        stateEvent.categoryName.length < 2 -> R.string.error_name_too_short
        stateEvent.categoryName.length > 30 -> R.string.error_name_too_long
        else -> null
    }
}