package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import androidx.lifecycle.SavedStateHandle
import com.github.sgeorgiev24.leaf.interactor.validator.ValidatorStateEvent
import com.github.sgeorgiev24.leaf.interactor.validator.StringValidators
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCategoriesViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val stringValidators: StringValidators
) : BaseViewModel<EditCategoriesState, EditCategoriesAction, ScreenEvent>(
    savedStateHandle, EditCategoriesState()
) {
    override suspend fun handleActions(action: EditCategoriesAction) {
        when (action) {
            EditCategoriesAction.OnBack ->
                navigationDispatcher.navigateBack()
            is EditCategoriesAction.OnCategoryNameValueChange ->
                onCategoryNameValueChange(action.value)
            EditCategoriesAction.OnDoneActionClick ->
                submitEvent(ScreenEvent.ClearFocus)
            is EditCategoriesAction.OnCategoryTypeOptionSelected ->
                onCategoryTypeOptionSelected(uuid = action.uuid)
        }
    }

    private fun onCategoryTypeOptionSelected(uuid: String) {
        val selectedCategoryType = state.value.categoryTypeOptions.find { it.uuid == uuid }
        updateState {
            copy(selectedCategoryTypeOption = selectedCategoryType)
        }
    }

    private fun onCategoryNameValueChange(value: String) {
        val errorResId =
            stringValidators.validateCategoryName(ValidatorStateEvent.ValidateCategoryName(value))
        updateState { copy(categoryName = InputWrapper(value = value, errorResId = errorResId)) }
    }
}