package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel
@Inject constructor(
    private val navigationDispatcher: NavigationDispatcher,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BottomNavigationState, BottomNavigationAction, Unit>(
    savedStateHandle, BottomNavigationState()
) {
    override suspend fun handleActions(action: BottomNavigationAction) {
        when (action) {
            is BottomNavigationAction.OnBottomNavItemClick -> onBottomNavItemClick(action.navAction)
        }
    }
    private fun onBottomNavItemClick(navAction: NavigationAction) {
        when (navAction) {
        }
    }

    private fun navigateTo(destination: NavigationAction) {
        viewModelScope.launch { navigationDispatcher.navigateTo(destination) }
    }
}