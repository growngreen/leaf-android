package com.github.sgeorgiev24.leaf.model.extensions

inline fun <R, T> R.runIf(condition: Boolean, block: (R) -> T) {
    if (condition) {
        block(this)
    }
}

inline fun String.runIfNotBlank(block: (String) -> Unit) = runIf(this.isNotBlank(), block)
