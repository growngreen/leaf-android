package com.github.sgeorgiev24.leaf.presentation.view.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpViewModel

@Composable
fun SignUpScreen() {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val state by viewModel.state.collectAsState()

    RootScreen(
        viewModel = viewModel,
        content = {
            SignUpContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}