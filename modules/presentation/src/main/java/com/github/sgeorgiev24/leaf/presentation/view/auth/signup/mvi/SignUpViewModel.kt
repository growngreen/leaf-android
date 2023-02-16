package com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher
) : BaseViewModel<SignUpState, SignUpAction, Unit>(savedStateHandle, SignUpState()) {
    override suspend fun handleActions(action: SignUpAction) {
        when (action) {
            SignUpAction.OnBack ->
                navigationDispatcher.navigateBack()
        }
    }
}