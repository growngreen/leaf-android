package com.github.sgeorgiev24.leaf.network.category.model

@kotlinx.serialization.Serializable
data class CategoryDto(
    val userId: String,
    val title: String,
    val type: CategoryTypeDto,
    val icon: String
) {
    constructor() : this("", "", CategoryTypeDto.INCOME, "")
}

fun CategoryDto.toFirestoreDocument() = hashMapOf(
    "userId" to userId,
    "title" to title,
    "type" to type.name,
    "icon" to icon
)