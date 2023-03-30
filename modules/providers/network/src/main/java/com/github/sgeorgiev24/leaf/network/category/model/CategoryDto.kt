package com.github.sgeorgiev24.leaf.network.category.model

import com.github.sgeorgiev24.leaf.network.util.FirebaseConstants.CATEGORY_ICON
import com.github.sgeorgiev24.leaf.network.util.FirebaseConstants.CATEGORY_TITLE
import com.github.sgeorgiev24.leaf.network.util.FirebaseConstants.CATEGORY_TYPE
import com.github.sgeorgiev24.leaf.network.util.FirebaseConstants.USER_ID

@kotlinx.serialization.Serializable
data class CategoryDto(
    val id: String,
    val userId: String,
    val title: String,
    val type: CategoryTypeDto,
    val icon: String
) {
    constructor() : this("", "", "", CategoryTypeDto.INCOME, "")
}

fun CategoryDto.toFirestoreDocument() = hashMapOf(
    USER_ID to userId,
    CATEGORY_TITLE to title,
    CATEGORY_TYPE to type.name,
    CATEGORY_ICON to icon
)