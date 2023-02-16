package com.github.sgeorgiev24.leaf.presentation.common.util.validator

import com.github.sgeorgiev24.leaf.presentation.R

object NameValidator {
    fun getNameErrorOrNull(input: String) =
        when {
            input.isEmpty() -> R.string.error_name_cannot_be_empty
            input.length < 2 -> R.string.error_name_too_short
            input.length > 30 -> R.string.error_name_too_long
            else -> null
        }
}