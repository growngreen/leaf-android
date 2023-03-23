package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data.EditCategoriesTab

sealed class EditCategoriesAction {
    object OnBack : EditCategoriesAction()

    object OnDoneActionClick : EditCategoriesAction()

    data class OnCategoryNameValueChange(
        val value: String
    ) : EditCategoriesAction()

    data class OnCategoryTypeOptionSelected(val uuid: String) : EditCategoriesAction()

    data class OnCategoryTabClick(val tab: EditCategoriesTab) : EditCategoriesAction()

    data class OnCategoryIconClick(val icon: String) : EditCategoriesAction()
}