package com.github.sgeorgiev24.leaf.presentation.navigation

sealed class NavigationCommand {
    data class Navigate(val navAction: NavigationAction) : NavigationCommand()
    data class PopToDestination(
        val route: String,
        val inclusive: Boolean
    ) : NavigationCommand()

    object Back : NavigationCommand()
}
