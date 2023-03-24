package com.github.sgeorgiev24.leaf.repository.user.mapper

import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.network.auth.model.LeafUserDto

fun LeafUserDto.toDomain() = LeafUser(
    uid = uid,
    email = email,
    name = name
)

fun LeafUser.toEntity() = LeafUserEntity(
    uid = uid,
    email = email,
    name = name
)

fun LeafUserEntity.toDomain() = LeafUser(
    uid = uid,
    email = email,
    name = name
)