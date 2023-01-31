package com.github.sgeorgiev24.leaf.presentation.model

sealed class ComponentType {
    object Toast : ComponentType()
    object Dialog : ComponentType()
    object None : ComponentType()
    data class SnackBar(
        val undo: (() -> Unit)? = null,
        val dismiss: (() -> Unit)? = null
    ) : ComponentType()

    data class SnackBarNotification(
        val onClick: (() -> Unit)? = null
    ) : ComponentType()
}