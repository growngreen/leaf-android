package com.github.sgeorgiev24.leaf.presentation.navigation.destinations

import com.github.sgeorgiev24.leaf.presentation.navigation.NavAnimation
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

sealed class AuthDests : NavigationAction {
    object Loading : AuthDests() {
        override val route: String
            get() = "loading"
        override val navAnimation: NavAnimation
            get() = NavAnimation.horizontalSlide()
    }

    object SignUp : AuthDests() {
        override val route: String
            get() = "signUp"
        override val navAnimation: NavAnimation
            get() = NavAnimation.horizontalSlide()
    }
}
