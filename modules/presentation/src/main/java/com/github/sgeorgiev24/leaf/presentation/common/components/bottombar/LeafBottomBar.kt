package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi.BottomNavigationViewModel

@Composable
fun LeafBottomBar(
    navBackStackEntry: NavBackStackEntry
) {
    val viewModel = hiltViewModel<BottomNavigationViewModel>()
    val state by viewModel.state.collectAsState()

    LeafBottomBarContent(
        state = state,
        action = viewModel.submitAction,
        navBackStackEntry = navBackStackEntry
    )
}