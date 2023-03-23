package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.interactor.category.CategoryStateEvent
import com.github.sgeorgiev24.leaf.interactor.category.GetCategoryIcons
import com.github.sgeorgiev24.leaf.interactor.validator.ValidatorStateEvent
import com.github.sgeorgiev24.leaf.interactor.validator.StringValidators
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoriesViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val stringValidators: StringValidators,
    private val getCategoryIcons: GetCategoryIcons
) : BaseViewModel<EditCategoriesState, EditCategoriesAction, ScreenEvent>(
    savedStateHandle, EditCategoriesState()
) {
    init {
        viewModelScope.launch {
            getCategoryIcons()
        }
    }

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
            is EditCategoriesAction.OnCategoryTabClick ->
                updateState {
                    copy(selectedTab = action.tab)
                }
            is EditCategoriesAction.OnCategoryIconClick ->
                updateState {
                    copy(selectedCategoryIcon = action.icon)
                }
        }
    }

    private fun onCategoryTypeOptionSelected(uuid: String) {
        val selectedCategoryType = state.value.categoryTypeOptions.find { it.uuid == uuid }
        updateState {
            copy(selectedCategoryTypeOption = selectedCategoryType)
        }
    }

    private suspend fun getCategoryIcons() {
        val event = CategoryStateEvent.GetCategoryIcons
        if (canExecuteNewStateEvent(event)) {
            getCategoryIcons(event).run {
                data?.let {
                    updateState {
                        copy(categoryIcons = it, selectedCategoryIcon = it.firstOrNull())
                    }
                }
            }
        }
    }

    private fun onCategoryNameValueChange(value: String) {
        val errorResId =
            stringValidators.validateCategoryName(ValidatorStateEvent.ValidateCategoryName(value))
        updateState { copy(categoryName = InputWrapper(value = value, errorResId = errorResId)) }
    }
}