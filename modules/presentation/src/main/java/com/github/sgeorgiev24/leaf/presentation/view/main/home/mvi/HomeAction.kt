package com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi

sealed class HomeAction {
    object OnSignOutClick : HomeAction()
}