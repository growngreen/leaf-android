package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.interactor.category.AddCategory
import com.github.sgeorgiev24.leaf.interactor.category.CategoryStateEvent
import com.github.sgeorgiev24.leaf.interactor.category.GetAllCategories
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
    private val getCategoryIcons: GetCategoryIcons,
    private val addCategory: AddCategory,
    private val getAllCategories: GetAllCategories
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
            EditCategoriesAction.OnAddCategoryClick ->
                onAddCategoryClick()
        }
    }

    suspend fun getAllCategories() {
        val event = CategoryStateEvent.GetAllCategories
        if (canExecuteNewStateEvent(event)) {
            getAllCategories(event).run {
                data?.let {
                    Log.d("debug1914", "getAllCategories: $it")

                    updateState {
                        copy(categories = it)
                    }
                }
            }
        }
    }

    private suspend fun onAddCategoryClick() {
        val event = CategoryStateEvent.AddCategory(
            title = state.value.categoryName.value,
            type = state.value.selectedCategoryTypeOption?.categoryType,
            icon = state.value.selectedCategoryIcon
        )
        if (canExecuteNewStateEvent(event)) {
            addCategory(event).run {
                data?.let {
                    navigationDispatcher.navigateBack()
                }
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