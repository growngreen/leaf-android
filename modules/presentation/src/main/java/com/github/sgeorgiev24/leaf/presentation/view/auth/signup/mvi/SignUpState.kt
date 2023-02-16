package com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi

import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper

data class SignUpState(
    val name: InputWrapper = InputWrapper(),
    val email: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper(),
    val confirmPassword: InputWrapper = InputWrapper(),
) {
    val isSignUpButtonEnabled
        get() = name.isValid && email.isValid && password.isValid && confirmPassword.isValid
}
