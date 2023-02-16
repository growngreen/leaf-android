package com.github.sgeorgiev24.leaf.presentation.common.util.validator

import android.util.Patterns
import com.github.sgeorgiev24.leaf.presentation.R

object EmailValidator {
    fun getEmailErrorOrNull(input: String) =
        when {
            input.isEmpty() -> R.string.error_email_cannot_be_empty
            !Patterns.EMAIL_ADDRESS.matcher(input).matches() -> R.string.error_email_invalid
            else -> null
        }
}