package com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi

sealed class SignUpAction {
    object OnBack : SignUpAction()
    object OnSignUpClick : SignUpAction()
    object OnSignInLinkClick : SignUpAction()
    object OnNextActionClick : SignUpAction()
    object OnDoneActionClick : SignUpAction()
    data class OnEmailValueChange(val value: String) : SignUpAction()
    data class OnPasswordValueChange(val value: String) : SignUpAction()
    data class OnConfirmPasswordValueChange(val value: String) : SignUpAction()
    data class OnNameValueChange(val value: String) : SignUpAction()
}