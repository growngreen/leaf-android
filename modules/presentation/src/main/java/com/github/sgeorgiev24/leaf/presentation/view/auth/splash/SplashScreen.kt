package com.github.sgeorgiev24.leaf.presentation.view.auth.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.view.auth.splash.mvi.SplashViewModel

@Composable
fun SplashScreen() {
    val viewModel = hiltViewModel<SplashViewModel>()

    RootScreen(
        viewModel = viewModel,
        content = {
            SplashContent()
        }
    )
}