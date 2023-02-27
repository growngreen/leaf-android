package com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.interactor.auth.AuthStateEvent
import com.github.sgeorgiev24.leaf.interactor.auth.GetUser
import com.github.sgeorgiev24.leaf.interactor.auth.SignOut
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.AuthDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.MainDests
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val getUser: GetUser,
    private val signOut: SignOut
) : BaseViewModel<HomeState, HomeAction, Unit>(savedStateHandle, HomeState()) {

    override suspend fun handleActions(action: HomeAction) {
        when (action) {
            HomeAction.OnSignOutClick ->
                signOut()
            HomeAction.OnEditCategoriesClick ->
                navigationDispatcher.navigateTo(MainDests.EditCategories)
        }
    }

    private suspend fun signOut() {
        signOut(AuthStateEvent.SignOut).run {
            data?.let {
                navigationDispatcher.navigateTo(AuthDests.SignIn)
            }
        }
    }

    fun getUser() {
        getUser(AuthStateEvent.GetUser).run {
            data?.let {
                updateState { copy(user = it) }
            }
        }
    }
}