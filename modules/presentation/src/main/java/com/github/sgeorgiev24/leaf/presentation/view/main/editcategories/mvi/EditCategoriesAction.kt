package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

sealed class EditCategoriesAction {
    object OnBack : EditCategoriesAction()

    object OnDoneActionClick : EditCategoriesAction()

    data class OnCategoryNameValueChange(
        val value: String
    ) : EditCategoriesAction()
}