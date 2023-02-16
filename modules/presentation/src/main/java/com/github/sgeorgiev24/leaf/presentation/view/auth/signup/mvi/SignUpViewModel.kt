package com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.interactor.auth.AuthStateEvent
import com.github.sgeorgiev24.leaf.interactor.auth.SignUp
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.common.util.validator.EmailValidator
import com.github.sgeorgiev24.leaf.presentation.common.util.validator.NameValidator
import com.github.sgeorgiev24.leaf.presentation.common.util.validator.PasswordValidator
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val signUp: SignUp
) : BaseViewModel<SignUpState, SignUpAction, ScreenEvent>(savedStateHandle, SignUpState()) {

    override suspend fun handleActions(action: SignUpAction) {
        when (action) {
            SignUpAction.OnBack ->
                navigationDispatcher.navigateBack()
            SignUpAction.OnDoneActionClick ->
                submitEvent(ScreenEvent.ClearFocus)
            SignUpAction.OnNextActionClick ->
                submitEvent(ScreenEvent.MoveFocus())
            SignUpAction.OnSignUpClick ->
                signUp()
            SignUpAction.OnSignInLinkClick -> {}
            is SignUpAction.OnConfirmPasswordValueChange ->
                onConfirmPasswordValueChange(action.value)
            is SignUpAction.OnEmailValueChange ->
                onEmailValueChange(action.value)
            is SignUpAction.OnNameValueChange ->
                onNameValueChange(action.value)
            is SignUpAction.OnPasswordValueChange ->
                onPasswordValueChange(action.value)
        }
    }

    private suspend fun signUp() {
        val event = AuthStateEvent.SignUp(
            email = state.value.email.value,
            name = state.value.name.value,
            password = state.value.password.value
        )
        if (canExecuteNewStateEvent(event)) {
            signUp(event).run {
                data?.let { Timber.i("Signed up successfully") }
                // TODO: navigate to home/sign in screen
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private fun onPasswordValueChange(value: String) {
        val errorResId = PasswordValidator.getPasswordErrorOrNull(value)
        updateState {
            copy(
                password = InputWrapper(value = value, errorResId = errorResId)
            )
        }
    }

    private fun onConfirmPasswordValueChange(value: String) {
        updateState {
            copy(
                confirmPassword = InputWrapper(value = value)
            )
        }
    }

    private fun onEmailValueChange(value: String) {
        val errorResId = EmailValidator.getEmailErrorOrNull(value)
        updateState {
            copy(
                email = InputWrapper(value = value, errorResId = errorResId)
            )
        }
    }

    private fun onNameValueChange(value: String) {
        val errorResId = NameValidator.getNameErrorOrNull(value)
        updateState {
            copy(
                name = InputWrapper(value = value, errorResId = errorResId)
            )
        }
    }
}