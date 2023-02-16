package com.github.sgeorgiev24.leaf.network.auth

interface SignInListener {
    fun signInSuccess(email: String?, password: String?)
    fun signInFailure(exception: Exception?, email: String?, password: String?)
}