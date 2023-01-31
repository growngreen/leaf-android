package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi

import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.BottomNavigationItem

data class BottomNavigationState(
    val bottomNavItems: List<BottomNavigationItem> = BottomNavigationItem.values().toList(),
    val isUserLoggedIn: Boolean = false
)
