package com.github.sgeorgiev24.leaf.repository.extensions

import com.github.sgeorgiev24.leaf.model.state.*
import com.github.sgeorgiev24.leaf.network.util.NetworkConst.NETWORK_DATA_NULL
import com.github.sgeorgiev24.leaf.network.util.NetworkConst.NETWORK_ERROR
import com.github.sgeorgiev24.leaf.network.util.NetworkConst.NETWORK_ERROR_UNAUTHORIZED
import com.github.sgeorgiev24.leaf.network.util.NetworkConst.NETWORK_REQUEST_CANCELED
import com.github.sgeorgiev24.leaf.network.util.NetworkResult

const val EMPTY_RESPONSE = "204"

fun <T> NetworkResult<T>.toDataState(
    event: StateEvent,
    // Default, used if we will display data without displaying anything
    successMessage: Success? = null,
): DataState<T> {
    return when (this) {
        NetworkResult.CancellationError -> buildError(
            NETWORK_REQUEST_CANCELED,
            event,
            Error.General.Timeout
        )
        is NetworkResult.Error -> buildError(
            message = this.message,
            stateEvent = event,
            messageType = Error.General.ApiMessage(message = this.message ?: "")
        )
        NetworkResult.NetworkError -> buildError(
            NETWORK_ERROR,
            event,
            Error.General.Network
        )
        NetworkResult.Unauthorized -> buildError(
            NETWORK_ERROR_UNAUTHORIZED,
            event,
            Error.Auth.Unauthorized
        )
        NetworkResult.Empty -> DataState.data(
            response = Response(
                message = EMPTY_RESPONSE,
                messageType = Success.Empty,
            ),
            data = null,
            stateEvent = event
        )
        is NetworkResult.Success -> {
            if (this.data == null && this.data != Unit)
                buildError(
                    "Reason: $NETWORK_DATA_NULL.",
                    event
                )
            else buildSuccessData(this.data, event, successMessage)
        }
    }
}

fun <T, A> NetworkResult<T>.toDataState(
    event: StateEvent,
    // Default, used if we will display data without displaying anything
    successMessage: Success? = null,
    map: (T) -> A
): DataState<A> {
    return when (this) {
        NetworkResult.CancellationError -> buildError(
            NETWORK_REQUEST_CANCELED,
            event,
            Error.General.Timeout // TODO maybe change this.
        )
        is NetworkResult.Error -> buildError(
            message = this.message,
            stateEvent = event,
            messageType = Error.General.ApiMessage(
                message = this.message ?: ""
            )
        )
        NetworkResult.NetworkError -> buildError(
            NETWORK_ERROR,
            event,
            Error.General.Network
        )
        NetworkResult.Unauthorized -> buildError(
            NETWORK_ERROR_UNAUTHORIZED,
            event,
            Error.Auth.Unauthorized
        )
        NetworkResult.Empty -> DataState.data(
            response = Response(
                message = EMPTY_RESPONSE,
                messageType = Success.Empty,
            ),
            data = null,
            stateEvent = event
        )
        is NetworkResult.Success -> {
            if (this.data == null && this.data != Unit)
                buildError(
                    "Reason: $NETWORK_DATA_NULL.",
                    event
                )
            else buildSuccessData(
                map(this.data),
                event,
                successMessage
            )
        }
    }
}