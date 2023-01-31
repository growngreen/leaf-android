package com.github.sgeorgiev24.leaf.presentation.navigation.wrapper

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.material.bottomSheet

fun NavGraphBuilder.composableHolder(
    action: NavigationAction,
    content: @Composable () -> Unit
) {
    composable(
        route = action.route,
        arguments = action.arguments,
        enterTransition = action.navAnimation?.enterTransition,
        exitTransition = action.navAnimation?.exitTransition,
        popEnterTransition = action.navAnimation?.popEnterTransition,
        popExitTransition = action.navAnimation?.popExitTransition
    ) {
        content()
    }
}

fun NavGraphBuilder.composableHolderWithArgs(
    action: NavigationAction,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = action.route,
        arguments = action.arguments,
        enterTransition = action.navAnimation?.enterTransition,
        exitTransition = action.navAnimation?.exitTransition,
        popEnterTransition = action.navAnimation?.popEnterTransition,
        popExitTransition = action.navAnimation?.popExitTransition
    ) { navBackStack ->
        content(navBackStack)
    }
}

fun NavGraphBuilder.bottomSheetHolder(
    action: NavigationAction,
    content: @Composable () -> Unit
) {
    bottomSheet(
        route = action.route,
        arguments = action.arguments,
    ) {
        content()
    }
}

fun NavGraphBuilder.bottomSheetHolderWithArgs(
    action: NavigationAction,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    bottomSheet(
        route = action.route,
        arguments = action.arguments,
    ) { navBackStack ->
        content(navBackStack)
    }
}