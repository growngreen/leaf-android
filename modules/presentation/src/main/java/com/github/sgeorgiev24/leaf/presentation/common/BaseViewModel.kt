package com.github.sgeorgiev24.leaf.presentation.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev24.leaf.model.state.Response
import com.github.sgeorgiev24.leaf.model.state.StateEvent
import com.github.sgeorgiev24.leaf.model.state.StateEventManager
import com.github.sgeorgiev24.leaf.model.util.Queue
import com.github.sgeorgiev24.leaf.presentation.common.util.toUiEvent
import com.github.sgeorgiev24.leaf.presentation.model.ComponentType
import com.github.sgeorgiev24.leaf.presentation.model.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<ScreenState : Any, Action, Event>(
    val savedStateHandle: SavedStateHandle,
    initialState: ScreenState,
) : ViewModel() {

    @PublishedApi
    internal val mState: MutableStateFlow<ScreenState> = MutableStateFlow(initialState)
    val state: StateFlow<ScreenState> = mState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = initialState,
    )

    private val stateEventManager =
        StateEventManager()
    val isLoading = stateEventManager.shouldDisplayProgressBar

    val queue: MutableState<Queue<UiEvent>> =
        mutableStateOf(Queue(mutableListOf()))

    private val actions = MutableSharedFlow<Action>()

    private val _events = Channel<Event>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        collectActions()
    }

    open suspend fun handleActions(action: Action) {
        // To or not to override, some screens mights not have actions
    }

    private fun collectActions() = viewModelScope.launch {
        actions.collect { handleActions(it) }
    }

    val submitAction: (action: Action) -> Unit = {
        viewModelScope.launch { actions.emit(it) }
    }

    val submitEvent: (event: Event) -> Unit = {
        viewModelScope.launch { _events.send(it) }
    }

    inline fun updateAndGetState(crossinline reduce: ScreenState.() -> ScreenState): ScreenState {
        return mState.updateAndGet(reduce)
    }

    inline fun updateState(crossinline function: ScreenState.() -> ScreenState) {
        mState.update(function)
    }

    fun <T> getFromSavedStateAsFlow(key: String) = savedStateHandle.getLiveData<T?>(key).asFlow()

    fun <T> getSavedStateAsFlow(key: String) = savedStateHandle.getStateFlow(key, "")

    fun <T> getFromSavedState(key: String) = savedStateHandle.get<T>(key)

    fun <T> saveToSavedState(key: String, value: T?) = savedStateHandle.set(key, value)

    private fun <T> LiveData<T?>.asFlow(): Flow<T?> = callbackFlow {
        val observer = Observer<T?> { value -> trySend(value) }
        observeForever(observer)
        awaitClose {
            removeObserver(observer)
        }
    }

    fun appendToMessageQueue(uiEvent: UiEvent) {
        if (!checkIfExists(
                queue = this.queue.value, response = uiEvent
            )
        ) {
            val queue = this.queue.value
            queue.add(uiEvent)
            this.queue.value =
                Queue(mutableListOf()) // force recompose
            this.queue.value = queue
        }
    }

    fun removeHeadMessage() {
        try {
            val queue = this.queue.value
            queue.remove() // can throw exception if empty
            this.queue.value =
                Queue(mutableListOf()) // force recompose
            this.queue.value = queue
        } catch (e: Exception) {
            Timber.d("Nothing to remove from DialogQueue")
        }
    }

    fun canExecuteNewStateEvent(stateEvent: StateEvent): Boolean {
        // If a job is already active, do not allow duplication
        if (isJobAlreadyActive(stateEvent)) {
            return false
        }
        // if a dialog is showing, do not allow new StateEvents
        if (!isMessageStackEmpty()) {
            return false
        }
        return true
    }

    internal fun Response.handleNewResponse(
        componentType: ComponentType = ComponentType.SnackBar()
    ) {
        if (this.message.isNotEmpty()) {
            appendToMessageQueue(this.messageType.toUiEvent(componentType))
        }
    }

    internal fun handleNewUiEvent(uiEvent: UiEvent) {
        appendToMessageQueue(uiEvent)
    }

    fun addStateEvent(stateEvent: StateEvent) = stateEventManager.addStateEvent(stateEvent)

    fun removeStateEvent(stateEvent: StateEvent?) =
        stateEventManager.removeStateEvent(stateEvent)

    private fun isStateEventActive(stateEvent: StateEvent) =
        stateEventManager.isStateEventActive(stateEvent)

    private fun isJobAlreadyActive(stateEvent: StateEvent): Boolean {
        return isStateEventActive(stateEvent)
    }

    private fun isMessageStackEmpty(): Boolean {
        return queue.value.isEmpty()
    }

    fun clearStateMessage(index: Int = 0) {
        Timber.d("DataChannelManager clear state message")
        queue.value.removeAt(index)
    }

    fun clearAllStateMessages() = queue.value.clear()

    private fun checkIfExists(
        response: UiEvent,
        queue: Queue<UiEvent>
    ): Boolean {
        if (queue.items.contains(response)) {
            return true
        }
        return false
    }
}