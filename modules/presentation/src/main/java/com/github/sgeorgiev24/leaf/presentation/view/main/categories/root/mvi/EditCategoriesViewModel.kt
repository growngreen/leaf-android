package com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.interactor.category.AddCategory
import com.github.sgeorgiev24.leaf.interactor.category.CategoryStateEvent
import com.github.sgeorgiev24.leaf.interactor.category.DeleteCategory
import com.github.sgeorgiev24.leaf.interactor.category.GetAllCategories
import com.github.sgeorgiev24.leaf.interactor.category.GetCategoryIcons
import com.github.sgeorgiev24.leaf.interactor.category.cache.SetCachedCategory
import com.github.sgeorgiev24.leaf.interactor.validator.StringValidators
import com.github.sgeorgiev24.leaf.interactor.validator.ValidatorStateEvent
import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.presentation.common.BaseViewModel
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.CategoryDests
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
    private val getAllCategories: GetAllCategories,
    private val deleteCategory: DeleteCategory,
    private val setCachedCategory: SetCachedCategory
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
            is EditCategoriesAction.CollapseCategory ->
                updateState {
                    copy(revealedCategory = null)
                }
            is EditCategoriesAction.ExpandCategory ->
                updateState {
                    copy(revealedCategory = action.category)
                }
            is EditCategoriesAction.OnDeleteCategoryClick ->
                onDeleteCategory(action.categoryId)
            is EditCategoriesAction.OnEditCategoryClick ->
                onEditCategory(action.category)
        }
    }

    suspend fun getAllCategories() {
        val event = CategoryStateEvent.GetAllCategories
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            getAllCategories(event).run {
                data?.let {
                    updateState {
                        copy(categories = it)
                    }
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private suspend fun onEditCategory(category: Category) {
        val event = CategoryStateEvent.SetCachedCategory(category)
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            setCachedCategory(event).run {
                data?.let {
                    navigationDispatcher.navigateTo(CategoryDests.EditCategory)
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
            }
        }
    }

    private suspend fun onDeleteCategory(categoryId: String) {
        val event = CategoryStateEvent.DeleteCategory(categoryId)
        if (canExecuteNewStateEvent(event)) {
            addStateEvent(event)
            deleteCategory(stateEvent = event).run {
                data?.let {
                    getAllCategories()
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
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
            addStateEvent(event)
            addCategory(event).run {
                data?.let {
                    navigationDispatcher.navigateBack()
                }
                response?.handleNewResponse()
                stateEvent?.let { removeStateEvent(it) }
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
            addStateEvent(event)
            getCategoryIcons(event).run {
                data?.let {
                    updateState {
                        copy(categoryIcons = it, selectedCategoryIcon = it.firstOrNull())
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