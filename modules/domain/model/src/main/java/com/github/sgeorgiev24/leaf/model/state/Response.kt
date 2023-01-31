package com.github.sgeorgiev24.leaf.model.state

data class Response(
    val message: String,
    val messageType: MessageType,
    val errors: Map<String, String>? = null
)

/**
 * It's intended to avoid ENUM class here and use with sub-classes
 *  with attempt to be able to separate this if it gets to big
 */
sealed interface MessageType

sealed class Error : MessageType {
    sealed class General : Error() {
        // Used when we can't find this error as a fallback
        object Unknown : Error()
        object NotImplemented : Error()
        // Used when we want to display the error message from the API
        data class ApiMessage(val message: String) : Error()

        object Timeout : Error()
        object Network : Error()
    }

    sealed class Auth : Error() {
        object WrongCredentials : Auth()
        object Unauthorized : Auth()
    }
}

sealed class Success : MessageType {
    data class Message(val message: String) : Success()
    object Empty : Success()

    sealed class Auth : Success() {
        object SignIn : Auth()
    }
}

sealed class Info : MessageType

sealed class None : MessageType {
    object Empty : None()
}
