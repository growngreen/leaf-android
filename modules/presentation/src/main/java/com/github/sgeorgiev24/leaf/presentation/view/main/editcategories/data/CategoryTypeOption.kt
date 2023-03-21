package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data

import androidx.annotation.StringRes
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.model.dropdown.DropdownMenuOption
import java.util.*

data class CategoryTypeOption(
    override val uuid: String = UUID.randomUUID().toString(),
    @StringRes
    override val titleResId: Int,
    val categoryType: CategoryType
) : DropdownMenuOption

private val income = CategoryTypeOption(
    titleResId = R.string.edit_categories_income_type,
    categoryType = CategoryType.INCOME
)

private val expense = CategoryTypeOption(
    titleResId = R.string.edit_categories_expense_type,
    categoryType = CategoryType.EXPENSE
)

val categoryTypeMenuOptions = listOf(income, expense)