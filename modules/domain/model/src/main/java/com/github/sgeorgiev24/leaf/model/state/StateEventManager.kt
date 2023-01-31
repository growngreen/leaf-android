package com.github.sgeorgiev24.leaf.model.state

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import timber.log.Timber

/**
 * - Keeps track of active StateEvents in DataChannelManager
 * - Keeps track of whether the progress bar should show or not based on a boolean
 *      value in each StateEvent (shouldDisplayProgressBar)
 */
class StateEventManager {

    private val activeStateEvents: HashMap<String, StateEvent> = HashMap()

    private val _shouldDisplayProgressBar: MutableState<Boolean> = mutableStateOf(false)

    val shouldDisplayProgressBar: State<Boolean>
        get() = _shouldDisplayProgressBar

    fun getActiveJobNames(): MutableSet<String> {
        return activeStateEvents.keys
    }

    fun clearActiveStateEventCounter() {
        Timber.d("DCM Clear active state events")
        activeStateEvents.clear()
        syncNumActiveStateEvents()
    }

    fun addStateEvent(stateEvent: StateEvent) {
        activeStateEvents[stateEvent.eventName()] = stateEvent
        syncNumActiveStateEvents()
    }

    fun removeStateEvent(stateEvent: StateEvent?) {
        Timber.d("DCM sem remove state event: ${stateEvent?.eventName()}")
        activeStateEvents.remove(stateEvent?.eventName())
        syncNumActiveStateEvents()
    }

    @SuppressLint("BinaryOperationInTimber")
    fun isStateEventActive(stateEvent: StateEvent): Boolean {
        Timber.d(
            "DCM sem state event active? " +
                "${activeStateEvents.containsKey(stateEvent.eventName())}"
        )
        return activeStateEvents.containsKey(stateEvent.eventName())
    }

    private fun syncNumActiveStateEvents() {
        var shouldDisplayProgressBar = false
        for (stateEvent in activeStateEvents.values) {
            if (stateEvent.shouldDisplayProgressBar()) {
                shouldDisplayProgressBar = true
            }
        }
        _shouldDisplayProgressBar.value = shouldDisplayProgressBar
    }
}
