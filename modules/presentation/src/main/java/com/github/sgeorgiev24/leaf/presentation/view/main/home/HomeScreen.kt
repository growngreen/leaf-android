package com.github.sgeorgiev24.leaf.presentation.view.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeViewModel
import com.github.sgeorgiev24.leaf.ui.lifecycle.ObserverLifecycleEvents
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    ObserverLifecycleEvents(
        onResume = {
            coroutineScope.launch {
                viewModel.getUser()
            }
        }
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