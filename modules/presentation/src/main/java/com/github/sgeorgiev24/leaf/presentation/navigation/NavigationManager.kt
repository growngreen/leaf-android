package com.github.sgeorgiev24.leaf.presentation.navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationManager {
    val navActions: SharedFlow<NavigationCommand>
    suspend fun navigate(command: NavigationCommand)
}