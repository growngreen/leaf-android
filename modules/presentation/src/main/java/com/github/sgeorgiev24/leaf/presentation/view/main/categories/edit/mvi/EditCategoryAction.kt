package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi

sealed class EditCategoryAction {
    object OnBackClick : EditCategoryAction()
    object OnSaveClick : EditCategoryAction()
    data class OnCategoryNameChange(val categoryName: String) : EditCategoryAction()
    data class OnCategoryIconChange(val categoryIcon: String) : EditCategoryAction()
}