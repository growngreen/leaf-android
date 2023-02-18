package com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi

import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper

data class SignInState(
    val email: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper()
) {
    val isSignInButtonEnabled
        get() = email.isValid && password.isValid
}
