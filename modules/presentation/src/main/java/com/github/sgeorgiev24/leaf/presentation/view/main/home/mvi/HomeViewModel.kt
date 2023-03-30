package com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.interactor.auth.AuthStateEvent
import com.github.sgeorgiev24.leaf.interactor.auth.SignOut
import com.github.sgeorgiev24.leaf.interactor.user.GetCachedUser
import com.github.sgeorgiev24.leaf.interactor.user.UserStateEvent
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.AuthDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.CategoryDests
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val getCachedUser: GetCachedUser,
    private val signOut: SignOut
) : BaseViewModel<HomeState, HomeAction, Unit>(savedStateHandle, HomeState()) {

    override suspend fun handleActions(action: HomeAction) {
        when (action) {
            HomeAction.OnSignOutClick ->
                signOut()
            HomeAction.OnEditCategoriesClick ->
                navigationDispatcher.navigateTo(CategoryDests.EditCategories)
        }
    }

    private suspend fun signOut() {
        signOut(AuthStateEvent.SignOut).run {
            data?.let {
                navigationDispatcher.navigateTo(AuthDests.SignIn)
            }
        }
    }

    suspend fun getUser() {
        getCachedUser(UserStateEvent.GetCachedUser).run {
            data?.let {
                updateState { copy(user = it) }
            }
        }
    }
}