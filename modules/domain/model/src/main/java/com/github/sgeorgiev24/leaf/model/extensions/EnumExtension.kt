package com.github.sgeorgiev24.leaf.model.extensions

inline fun <reified T : Enum<T>> String.asEnumOrDefault(defaultValue: T): T =
    enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue

inline fun <reified T : Enum<T>> String.asEnumOrNull(defaultValue: T): T? =
    enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }

inline fun <reified T : Enum<T>> String.asEnumOrDefault(
    defaultValue: T,
    comparable: (T, String) -> Boolean
): T =
    enumValues<T>().firstOrNull { comparable(it, this) } ?: defaultValue