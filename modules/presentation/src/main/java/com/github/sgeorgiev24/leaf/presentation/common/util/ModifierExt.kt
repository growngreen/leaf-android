package com.github.sgeorgiev24.leaf.presentation.common.util

import androidx.compose.ui.Modifier

fun Modifier.conditional(condition: Boolean, block: Modifier.() -> Modifier) =
    if (condition) {
        then(block(Modifier))
    } else {
        this
    }