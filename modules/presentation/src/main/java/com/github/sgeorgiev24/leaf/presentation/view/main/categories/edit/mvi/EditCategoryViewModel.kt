package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel
@Inject
constructor(
    savedstate: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher
) : BaseViewModel<EditCategoryState, EditCategoryAction, Unit>(savedstate, EditCategoryState()) {

    override suspend fun handleActions(action: EditCategoryAction) {
        when (action) {
            EditCategoryAction.OnBackClick ->
                navigationDispatcher.navigateBack()
            is EditCategoryAction.OnCategoryIconChange -> TODO()
            is EditCategoryAction.OnCategoryNameChange -> TODO()
            EditCategoryAction.OnSaveClick -> TODO()
        }
    }
}
