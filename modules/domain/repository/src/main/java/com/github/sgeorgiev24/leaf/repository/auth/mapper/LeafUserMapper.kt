package com.github.sgeorgiev24.leaf.repository.auth.mapper

import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.network.auth.model.LeafUserDto

fun LeafUserDto.toDomain() = LeafUser(
    email = email,
    name = name
)