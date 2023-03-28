package com.github.sgeorgiev24.leaf.interactor.user

import com.github.sgeorgiev24.leaf.model.state.StateEvent

sealed class UserStateEvent : StateEvent {
    object GetUser : UserStateEvent() {
        override fun errorInfo() = "Error while trying to get leaf user"
        override fun shouldDisplayProgressBar() = true
    }

    object GetCachedUser : UserStateEvent() {
        override fun errorInfo() = "Error while trying to get cached leaf user"
        override fun shouldDisplayProgressBar() = true
    }
}
