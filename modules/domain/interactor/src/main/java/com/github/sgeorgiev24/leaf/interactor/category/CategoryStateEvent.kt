package com.github.sgeorgiev24.leaf.interactor.category

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
}