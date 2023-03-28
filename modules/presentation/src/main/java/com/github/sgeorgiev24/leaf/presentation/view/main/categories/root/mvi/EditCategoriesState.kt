package com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.mvi

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.data.CategoryTypeOption
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.data.EditCategoriesTab
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.data.categoryTypeMenuOptions

data class EditCategoriesState(
    val categoryName: InputWrapper = InputWrapper(),
    val categoryTypeOptions: List<CategoryTypeOption> = categoryTypeMenuOptions,
    val selectedCategoryTypeOption: CategoryTypeOption? = null,
    val categoryIcons: List<String> = emptyList(),
    val selectedCategoryIcon: String? = null,
    val selectedTab: EditCategoriesTab = EditCategoriesTab.CATEGORIES_LIST,
    val categories: List<Category> = emptyList(),
    val revealedCategory: Category? = null
)