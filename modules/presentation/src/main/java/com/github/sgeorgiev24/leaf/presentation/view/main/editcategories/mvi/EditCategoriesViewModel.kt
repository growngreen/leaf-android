package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCategoriesViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher
) : BaseViewModel<EditCategoriesState, EditCategoriesAction, Unit>(
    savedStateHandle, EditCategoriesState()
) {
    override suspend fun handleActions(action: EditCategoriesAction) {
        when (action) {
            EditCategoriesAction.OnBack ->
                navigationDispatcher.navigateBack()
        }
    }
}