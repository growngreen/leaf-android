package com.github.sgeorgiev24.leaf.presentation.model

import androidx.annotation.StringRes

data class UiEvent(
    val message: String,
    @StringRes
    val messageResId: Int? = null,
    val componentType: ComponentType,
)
