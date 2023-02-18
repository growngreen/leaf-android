package com.github.sgeorgiev24.leaf.presentation.navigation.destinations

import com.github.sgeorgiev24.leaf.presentation.navigation.NavAnimation
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

sealed class AuthDests : NavigationAction {
    object SignUp : AuthDests() {
        override val route: String
            get() = "signUp"
        override val navAnimation: NavAnimation
            get() = NavAnimation.horizontalSlide()
    }

    object SignIn : AuthDests() {
        override val route: String
            get() = "signIn"
        override val navAnimation: NavAnimation
            get() = NavAnimation.horizontalSlide()
    }
}
