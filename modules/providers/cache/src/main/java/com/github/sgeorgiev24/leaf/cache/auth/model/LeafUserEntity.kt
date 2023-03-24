package com.github.sgeorgiev24.leaf.cache.auth.model

data class LeafUserEntity(
    val uid: String,
    val email: String?,
    val name: String?
) {
    companion object {
        val default = LeafUserEntity("", "", "")
    }
}
