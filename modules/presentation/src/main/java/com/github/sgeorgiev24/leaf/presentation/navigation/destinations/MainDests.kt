package com.github.sgeorgiev24.leaf.presentation.navigation.destinations

import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

sealed class MainDests : NavigationAction {
    object Home : MainDests() {
        override val route: String
            get() = "home"
    }
}
