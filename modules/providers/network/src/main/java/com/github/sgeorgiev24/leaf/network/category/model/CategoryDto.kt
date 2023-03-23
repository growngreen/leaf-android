package com.github.sgeorgiev24.leaf.network.category.model

data class CategoryDto(
    val title: String,
    val type: CategoryTypeDto,
    val icon: String
)

fun CategoryDto.toFirestoreDocument() = hashMapOf(
    "title" to title,
    "type" to type.text,
    "icon" to icon
)