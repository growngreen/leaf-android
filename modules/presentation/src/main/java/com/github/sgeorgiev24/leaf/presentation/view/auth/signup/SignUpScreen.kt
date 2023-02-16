package com.github.sgeorgiev24.leaf.presentation.view.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpViewModel

@Composable
fun SignUpScreen() {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val state by viewModel.state.collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    RootScreen(
        viewModel = viewModel,
        eventProcessor = { event ->
            when (event) {
                is ScreenEvent.MoveFocus ->
                    focusManager.moveFocus(focusDirection = FocusDirection.Down)
                else ->
                    focusManager.clearFocus()
            }
        },
        content = {
            SignUpContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}