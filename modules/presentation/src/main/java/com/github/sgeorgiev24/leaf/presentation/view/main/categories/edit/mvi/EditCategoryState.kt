package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi

import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.InputWrapper

data class EditCategoryState(
    val categoryId: String = "",
    val categoryName: InputWrapper = InputWrapper(),
    val selectedCategoryIcon: String = "",
    val categoryIcons: List<String> = emptyList()
) {
    val isSaveButtonEnabled
        get() = categoryName.isValid
}