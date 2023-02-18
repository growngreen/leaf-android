package com.github.sgeorgiev24.leaf.presentation.view.auth.splash.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.interactor.auth.AuthStateEvent
import com.github.sgeorgiev24.leaf.interactor.auth.GetUser
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.AuthDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.MainDests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val getUser: GetUser
) : BaseViewModel<Unit, Unit, Unit>(savedStateHandle, Unit) {

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    private suspend fun getUser() {
        getUser(AuthStateEvent.GetUser).run {
            delay(1000)
            data?.let {
                navigationDispatcher.navigateTo(MainDests.Home)
            } ?: navigationDispatcher.navigateTo(AuthDests.SignIn)
        }
    }
}