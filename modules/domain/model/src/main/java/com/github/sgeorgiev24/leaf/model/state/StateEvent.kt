package com.github.sgeorgiev24.leaf.model.state

interface StateEvent {

    fun errorInfo(): String

    fun eventName(): String = javaClass.simpleName

    fun shouldDisplayProgressBar(): Boolean
}