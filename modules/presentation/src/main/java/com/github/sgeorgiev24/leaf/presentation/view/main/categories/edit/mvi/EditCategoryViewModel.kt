package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.interactor.category.CategoryStateEvent
import com.github.sgeorgiev24.leaf.interactor.category.EditCategory
import com.github.sgeorgiev24.leaf.interactor.category.GetCategoryIcons
import com.github.sgeorgiev24.leaf.interactor.category.cache.GetCachedCategory
import com.github.sgeorgiev24.leaf.interactor.validator.StringValidators
import com.github.sgeorgiev24.leaf.interactor.validator.ValidatorStateEvent
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel
@Inject
constructor(
    savedstate: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val getCachedCategory: GetCachedCategory,
    private val getCategoryIcons: GetCategoryIcons,
    private val stringValidators: StringValidators,
    private val editCategory: EditCategory
) : BaseViewModel<EditCategoryState, EditCategoryAction, ScreenEvent>(
    savedstate, EditCategoryState()
) {

    init {
        viewModelScope.launch {
            launch { getCategory() }
            launch { getCategoryIcons() }
        }
    }

    override suspend fun handleActions(action: EditCategoryAction) {
        when (action) {
            EditCategoryAction.OnBackClick ->
                navigationDispatcher.navigateBack()
            is EditCategoryAction.OnCategoryIconClick ->
                updateState {
                    copy(selectedCategoryIcon = action.icon)
                }
            is EditCategoryAction.OnCategoryNameValueChange ->
                onCategoryNameValueChange(action.value)
            EditCategoryAction.OnSaveClick ->
                saveCategory()
            EditCategoryAction.OnDoneActionClick ->
                submitEvent(ScreenEvent.ClearFocus)
        }
    }

    private suspend fun saveCategory() {
        val event = CategoryStateEvent.EditCategory(
            categoryId = state.value.categoryId,
            categoryName = state.value.categoryName.value,
            categoryIcon = state.value.selectedCategoryIcon
        )
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            editCategory(event).run {
                data?.let {
                    navigationDispatcher.navigateBack()
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private suspend fun getCategory() {
        val event = CategoryStateEvent.GetCachedCategory
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            getCachedCategory(event).run {
                data?.let { category ->
                    updateState {
                        copy(
                            categoryId = category.id,
                            categoryName = InputWrapper(value = category.title),
                            selectedCategoryIcon = category.icon
                        )
                    }
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private suspend fun getCategoryIcons() {
        val event = CategoryStateEvent.GetCategoryIcons
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            getCategoryIcons(event).run {
                data?.let {
                    updateState {
                        copy(categoryIcons = it)
                    }
                    response?.handleNewResponse()
                    stateEvent?.let { removeStateEvent(it) }
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
