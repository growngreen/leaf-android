package com.github.sgeorgiev24.leaf.model.state

abstract class StateEventImpl : StateEvent {

    override fun eventName(): String {
        return javaClass.simpleName
    }
}