package com.github.sgeorgiev24.leaf.network.auth

import com.github.sgeorgiev24.leaf.network.auth.model.LeafUserDto
import com.github.sgeorgiev24.leaf.network.util.NetworkResult

interface AuthDataSource {
    suspend fun signUp(
        email: String,
        name: String,
        password: String
    ): NetworkResult<Unit>

    suspend fun signIn(
        email: String,
        password: String
    ): NetworkResult<Unit>

    suspend fun getUser(): NetworkResult<LeafUserDto?>
}