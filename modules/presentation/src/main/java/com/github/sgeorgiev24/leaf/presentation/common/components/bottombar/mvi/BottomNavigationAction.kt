package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi

import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

sealed class BottomNavigationAction {
    data class OnBottomNavItemClick(val navAction: NavigationAction) : BottomNavigationAction()
}
