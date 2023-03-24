package com.github.sgeorgiev24.leaf.network.category.model

data class CategoryDto(
    val userId: String,
    val title: String,
    val type: CategoryTypeDto,
    val icon: String
)

fun CategoryDto.toFirestoreDocument() = hashMapOf(
    "userId" to userId,
    "title" to title,
    "type" to type.text,
    "icon" to icon
)