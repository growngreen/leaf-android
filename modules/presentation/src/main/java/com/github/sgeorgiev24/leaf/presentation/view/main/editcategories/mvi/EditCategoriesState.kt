package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi

import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data.CategoryTypeOption
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data.categoryTypeMenuOptions

data class EditCategoriesState(
    val categoryName: InputWrapper = InputWrapper(),
    val categoryTypeOptions: List<CategoryTypeOption> = categoryTypeMenuOptions,
    val selectedCategoryTypeOption: CategoryTypeOption? = null,
    val categoryIcons: List<String> = emptyList()
)