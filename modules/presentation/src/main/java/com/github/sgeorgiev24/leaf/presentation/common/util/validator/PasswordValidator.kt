package com.github.sgeorgiev24.leaf.presentation.common.util.validator

import com.github.sgeorgiev24.leaf.presentation.R

object PasswordValidator {
    fun getConfirmPasswordErrorOrNull(
        password: String,
        confirmPassword: String
    ) = when {
        password != confirmPassword -> R.string.error_passwords_do_not_match
        confirmPassword.length < 6 -> R.string.error_password_too_short
        confirmPassword.length > 50 -> R.string.error_password_too_long
        else -> null
    }
}