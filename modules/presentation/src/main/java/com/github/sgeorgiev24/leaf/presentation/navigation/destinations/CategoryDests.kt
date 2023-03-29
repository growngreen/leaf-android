package com.github.sgeorgiev24.leaf.presentation.navigation.destinations

import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

sealed class CategoryDests : NavigationAction {
    object EditCategories : CategoryDests() {
        override val route: String
            get() = "editCategories"
    }

    object EditCategory : CategoryDests() {
        override val route: String
            get() = "editCategory"
    }
}