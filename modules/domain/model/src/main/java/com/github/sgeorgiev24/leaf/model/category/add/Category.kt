package com.github.sgeorgiev24.leaf.model.category.add

data class Category(
    val userId: String,
    val title: String,
    val type: CategoryType,
    val icon: String
)
