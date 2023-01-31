package com.github.sgeorgiev24.leaf.model.state

data class DataState<T>(
    var response: Response? = null,
    var data: T? = null,
    var stateEvent: StateEvent? = null
) {
    companion object {
        fun <T> error(
            response: Response,
            stateEvent: StateEvent?
        ): DataState<T> {
            return DataState(
                response = response,
                data = null,
                stateEvent = stateEvent
            )
        }

        fun <T> data(
            data: T? = null,
            stateEvent: StateEvent?,
            response: Response? = null,
        ): DataState<T> {
            return DataState(
                response = response,
                data = data,
                stateEvent = stateEvent
            )
        }
    }
}

fun <Result> buildSuccessData(
    data: Result,
    stateEvent: StateEvent,
    messageType: MessageType? = null
): DataState<Result> {
    return DataState.data(
        response = messageType?.let {
            Response(
                message = "",
                messageType = messageType
            )
        },
        data = data,
        stateEvent = stateEvent
    )
}

fun <Result> buildSuccess(
    message: String,
    stateEvent: StateEvent,
    messageType: MessageType = Success.Empty
): DataState<Result> {
    return DataState.data(
        stateEvent = stateEvent,
        response = Response(
            message = message,
            messageType = messageType,
        ),
        data = null,
    )
}

fun <Result> buildError(
    message: String,
    stateEvent: StateEvent,
    messageType: MessageType = Error.General.Unknown
): DataState<Result> {
    return DataState.error(
        stateEvent = stateEvent,
        response = Response(
            message = message,
            messageType = messageType,
        )
    )
}

fun <Result> buildError(
    stateEvent: StateEvent,
    messageType: MessageType = Error.General.Unknown,
    message: String?
): DataState<Result> {
    return DataState.error(
        stateEvent = stateEvent,
        response = Response(
            message = message ?: "",
            messageType = messageType
        )
    )
}

infix fun <T, U> DataState<T>.map(mapper: (T) -> (U)): DataState<U> {
    return DataState.data(
        response = this.response,
        data = this.data?.let { mapper(it) },
        stateEvent = this.stateEvent
    )
}

fun <T, A> DataState<T>.mapToError(
    stateEvent: StateEvent? = null
): DataState<A> {
    return DataState.data(
        data = null,
        response = this.response,
        stateEvent = stateEvent ?: this.stateEvent
    )
}
