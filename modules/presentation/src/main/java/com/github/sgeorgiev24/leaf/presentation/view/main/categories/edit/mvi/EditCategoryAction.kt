package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi

sealed class EditCategoryAction {
    object OnBackClick : EditCategoryAction()
    object OnSaveClick : EditCategoryAction()
    object OnDoneActionClick : EditCategoryAction()
    data class OnCategoryNameValueChange(val value: String) : EditCategoryAction()
    data class OnCategoryIconClick(val icon: String) : EditCategoryAction()
}