package com.github.sgeorgiev24.leaf.repository.user

import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.model.state.StateEvent

interface UserRepository {
    fun getUser(stateEvent: StateEvent): DataState<LeafUser?>

    suspend fun setCachedUser(stateEvent: StateEvent, user: LeafUser): DataState<LeafUser>

    suspend fun getCachedUser(stateEvent: StateEvent): DataState<LeafUser>

    suspend fun clearCachedUser(stateEvent: StateEvent): DataState<Unit>
}