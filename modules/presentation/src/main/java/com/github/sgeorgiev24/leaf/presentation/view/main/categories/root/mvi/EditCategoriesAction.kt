package com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.mvi

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.data.EditCategoriesTab

sealed class EditCategoriesAction {
    object OnBack : EditCategoriesAction()

    object OnDoneActionClick : EditCategoriesAction()

    object OnAddCategoryClick : EditCategoriesAction()

    data class OnDeleteCategoryClick(val categoryId: String) : EditCategoriesAction()

    data class OnEditCategoryClick(val category: Category) : EditCategoriesAction()

    data class OnCategoryNameValueChange(
        val value: String
    ) : EditCategoriesAction()

    data class OnCategoryTypeOptionSelected(val uuid: String) : EditCategoriesAction()

    data class OnCategoryTabClick(val tab: EditCategoriesTab) : EditCategoriesAction()

    data class OnCategoryIconClick(val icon: String) : EditCategoriesAction()

    data class CollapseCategory(val category: Category) : EditCategoriesAction()

    data class ExpandCategory(val category: Category) : EditCategoriesAction()
}