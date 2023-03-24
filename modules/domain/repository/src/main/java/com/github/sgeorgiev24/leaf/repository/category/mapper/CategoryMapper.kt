package com.github.sgeorgiev24.leaf.repository.category.mapper

import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.model.category.add.CategoryType
import com.github.sgeorgiev24.leaf.model.extensions.asEnumOrDefault
import com.github.sgeorgiev24.leaf.network.category.model.CategoryDto
import com.github.sgeorgiev24.leaf.network.category.model.CategoryTypeDto

fun Category.toDto() = CategoryDto(
    userId = userId,
    title = title,
    type = type.toDto(),
    icon = icon
)

fun CategoryDto.toDomain() = Category(
    userId = userId,
    title = title,
    type = type.toDomain(),
    icon = icon
)

fun CategoryType.toDto() = name.asEnumOrDefault(CategoryTypeDto.INCOME)
fun CategoryTypeDto.toDomain() = name.asEnumOrDefault(CategoryType.INCOME)