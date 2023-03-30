package com.github.sgeorgiev24.leaf.cache.category.model

data class CategoryEntity(
    val id: String,
    val userId: String,
    val title: String,
    val type: CategoryTypeEntity,
    val icon: String
) {
    companion object {
        val default = CategoryEntity("", "", "", CategoryTypeEntity.INCOME, "")
    }
}
