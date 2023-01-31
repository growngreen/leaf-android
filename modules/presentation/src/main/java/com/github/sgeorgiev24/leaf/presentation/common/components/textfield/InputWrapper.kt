package com.github.sgeorgiev24.leaf.presentation.common.components.textfield

import androidx.annotation.StringRes

data class InputWrapper(
    val value: String = "",
    @StringRes
    val errorResId: Int? = null
) {
    val isValid: Boolean
        get() = value.isNotBlank() && errorResId == null
}