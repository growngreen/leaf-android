package com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher
) : BaseViewModel<SignInState, SignInAction, ScreenEvent>(savedStateHandle, SignInState()) {
    override suspend fun handleActions(action: SignInAction) {
        when (action) {
            SignInAction.OnBack -> TODO()
            SignInAction.OnDoneActionClick -> TODO()
            SignInAction.OnNextActionClick -> TODO()
            SignInAction.OnSignInClick -> TODO()
            SignInAction.OnSignUpLinkClick -> TODO()
            is SignInAction.OnEmailValueChange -> TODO()
            is SignInAction.OnPasswordValueChange -> TODO()
        }
    }
}