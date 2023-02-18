package com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi

import com.github.sgeorgiev24.leaf.model.auth.LeafUser

data class HomeState(
    val user: LeafUser? = null
)