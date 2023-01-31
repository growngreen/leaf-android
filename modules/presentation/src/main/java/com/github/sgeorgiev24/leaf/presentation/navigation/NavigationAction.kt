package com.github.sgeorgiev24.leaf.presentation.navigation

import androidx.compose.animation.*
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavOptions

typealias enterAnim =
    (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)?

typealias exitAnim =
    (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)?

interface NavigationAction {
    val route: String
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
    val arguments: List<NamedNavArgument>
        get() = emptyList()
    val navAnimation: NavAnimation?
        get() = null
}

data class NavAnimation(
    val enterTransition: enterAnim,
    val exitTransition: exitAnim,
    val popEnterTransition: enterAnim,
    val popExitTransition: exitAnim
) {
    companion object {
        fun horizontalSlide() = NavAnimation(
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        )
    }
}