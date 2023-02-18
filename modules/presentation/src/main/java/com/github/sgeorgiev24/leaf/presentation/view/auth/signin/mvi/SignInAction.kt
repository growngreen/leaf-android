package com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi

sealed class SignInAction {
    object OnBack : SignInAction()
    object OnSignInClick : SignInAction()
    object OnSignUpLinkClick : SignInAction()
    object OnNextActionClick : SignInAction()
    object OnDoneActionClick : SignInAction()
    data class OnEmailValueChange(val value: String) : SignInAction()
    data class OnPasswordValueChange(val value: String) : SignInAction()
}