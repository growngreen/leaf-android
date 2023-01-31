package com.github.sgeorgiev24.leaf.presentation.navigation

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class NavigationDispatcher
@Inject
constructor(
    private val navigationManager: NavigationManager
) {

    val navigationCommands: Flow<NavigationCommand> = navigationManager.navActions

    suspend fun navigateTo(navAction: NavigationAction) {
        navigationManager.navigate(NavigationCommand.Navigate(navAction))
    }

    suspend fun navigateBack() {
        navigationManager.navigate(NavigationCommand.Back)
    }

    suspend fun popToDestination(navAction: NavigationAction, inclusive: Boolean) {
        navigationManager.navigate(NavigationCommand.PopToDestination(navAction.route, inclusive))
    }
}