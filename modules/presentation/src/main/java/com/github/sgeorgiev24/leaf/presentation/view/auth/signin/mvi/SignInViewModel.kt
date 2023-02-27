package com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.interactor.auth.AuthStateEvent
import com.github.sgeorgiev24.leaf.interactor.auth.SignIn
import com.github.sgeorgiev24.leaf.interactor.validator.ValidatorStateEvent
import com.github.sgeorgiev24.leaf.interactor.validator.Validators
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.AuthDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.MainDests
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val validators: Validators,
    private val signIn: SignIn
) : BaseViewModel<SignInState, SignInAction, ScreenEvent>(savedStateHandle, SignInState()) {
    override suspend fun handleActions(action: SignInAction) {
        when (action) {
            SignInAction.OnBack ->
                navigationDispatcher.navigateBack()
            SignInAction.OnDoneActionClick ->
                submitEvent(ScreenEvent.ClearFocus)
            SignInAction.OnNextActionClick ->
                submitEvent(ScreenEvent.MoveFocus())
            SignInAction.OnSignInClick ->
                signIn()
            SignInAction.OnSignUpLinkClick ->
                navigationDispatcher.navigateTo(AuthDests.SignUp)
            is SignInAction.OnEmailValueChange ->
                onEmailValueChange(action.value)
            is SignInAction.OnPasswordValueChange ->
                onPasswordValueChange(action.value)
        }
    }

    private suspend fun signIn() {
        val event = AuthStateEvent.SignIn(
            email = state.value.email.value,
            password = state.value.password.value
        )
        if (canExecuteNewStateEvent(event)) {
            signIn(event).run {
                data?.let {
                    Timber.i("Signed in successfully")
                    navigationDispatcher.navigateTo(MainDests.Home)
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private fun onEmailValueChange(value: String) {
        val errorResId = validators.getEmailErrorOrNull(ValidatorStateEvent.ValidateEmail(value))
        updateState {
            copy(
                email = InputWrapper(value = value, errorResId = errorResId)
            )
        }
    }

    private fun onPasswordValueChange(value: String) {
        val errorResId =
            validators.getPasswordErrorOrNull(ValidatorStateEvent.ValidatePassword(value))
        updateState {
            copy(
                password = InputWrapper(value = value, errorResId = errorResId)
            )
        }
    }
}