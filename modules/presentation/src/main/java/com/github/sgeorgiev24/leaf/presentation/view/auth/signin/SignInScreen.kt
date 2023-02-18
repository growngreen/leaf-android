package com.github.sgeorgiev24.leaf.presentation.view.auth.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi.SignInViewModel

@Composable
fun SignInScreen() {
    val viewModel = hiltViewModel<SignInViewModel>()
    val state by viewModel.state.collectAsState()

    val focusManager = LocalFocusManager.current

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
            SignInContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}