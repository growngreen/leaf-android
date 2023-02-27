package com.github.sgeorgiev24.leaf.presentation.view.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeViewModel
import com.github.sgeorgiev24.leaf.ui.lifecycle.ObserverLifecycleEvents

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    ObserverLifecycleEvents(
        onResume = { viewModel.getUser() }
    )

    RootScreen(
        viewModel = viewModel,
        content = {
            HomeContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}