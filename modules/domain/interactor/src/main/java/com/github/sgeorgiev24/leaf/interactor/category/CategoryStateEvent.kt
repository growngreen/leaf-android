package com.github.sgeorgiev24.leaf.interactor.category

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.category.add.CategoryType
import com.github.sgeorgiev24.leaf.model.state.StateEvent

sealed class CategoryStateEvent : StateEvent {
    object GetCategoryIcons : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to get category icons"
        override fun shouldDisplayProgressBar() = true
    }

    data class AddCategory(
        val title: String,
        val type: CategoryType?,
        val icon: String?
    ) : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to add category"
        override fun shouldDisplayProgressBar() = true
    }

    data class EditCategory(
        val categoryId: String,
        val categoryName: String,
        val categoryIcon: String
    ) : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to edit category"
        override fun shouldDisplayProgressBar() = true
    }

    data class DeleteCategory(
        val categoryId: String
    ) : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to delete category"
        override fun shouldDisplayProgressBar() = true
    }

    object GetAllCategories : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to get all categories"
        override fun shouldDisplayProgressBar() = true
    }

    data class SetCachedCategory(
        val category: Category
    ) : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to set cached category"
        override fun shouldDisplayProgressBar() = true
    }

    object GetCachedCategory : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to get cached category"
        override fun shouldDisplayProgressBar() = true
    }

    object ClearCachedCategory : CategoryStateEvent() {
        override fun errorInfo() = "Error while trying to clear cached category"
        override fun shouldDisplayProgressBar() = true
    }
}