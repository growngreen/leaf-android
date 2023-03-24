package com.github.sgeorgiev24.leaf.network.auth.model

import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUserDto() = LeafUserDto(
    uid = this.uid,
    email = email,
    name = displayName
)