package com.github.sgeorgiev24.leaf.repository.user

import com.github.sgeorgiev24.leaf.cache.auth.model.LeafUserEntity
import com.github.sgeorgiev24.leaf.cache.di.CacheLeafUserDataSource
import com.github.sgeorgiev24.leaf.cache.util.DefaultCacheDataSource
import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.network.auth.AuthDataSource
import com.github.sgeorgiev24.leaf.repository.user.mapper.toDomain
import com.github.sgeorgiev24.leaf.repository.extensions.toDataState
import com.github.sgeorgiev24.leaf.repository.user.mapper.toEntity
import javax.inject.Inject

class UserRepositoryImpl
@Inject
constructor(
    private val authDataSource: AuthDataSource,
    @CacheLeafUserDataSource
    private val leafUserCacheDataSource: DefaultCacheDataSource<LeafUserEntity>
) : UserRepository {
    override fun getUser(
        stateEvent: StateEvent
    ) = authDataSource
        .getUser()
        .toDataState(stateEvent) { it?.toDomain() }

    override suspend fun setCachedUser(stateEvent: StateEvent, user: LeafUser) =
        leafUserCacheDataSource
            .set(user.toEntity())
            .toDataState(stateEvent) { it.toDomain() }

    override suspend fun getCachedUser(stateEvent: StateEvent) =
        leafUserCacheDataSource
            .get()
            .toDataState(stateEvent) { it.toDomain() }

    override suspend fun clearCachedUser(stateEvent: StateEvent) =
        leafUserCacheDataSource
            .clear()
            .toDataState(stateEvent) {}
}