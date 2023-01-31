package com.github.sgeorgiev24.leaf.presentation.common.util

import androidx.annotation.StringRes
import com.github.sgeorgiev24.leaf.model.state.*
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.model.ComponentType
import com.github.sgeorgiev24.leaf.presentation.model.UiEvent

private const val EMPTY_VALUE = ""

fun MessageType.toUiEvent(
    componentType: ComponentType = ComponentType.SnackBar()
): UiEvent {
    return when (this) {
        is Error.General.ApiMessage -> this.message buildFromMessage componentType
        Error.Auth.WrongCredentials -> componentType buildFromClass R.string.ui_error_wrong_credentials
        Error.General.Unknown -> componentType buildFromClass R.string.ui_error_unknown
        Error.Auth.Unauthorized -> componentType buildFromClass R.string.ui_error_unauthorized
        Error.General.Network -> componentType buildFromClass R.string.ui_error_network
        Error.General.Timeout -> componentType buildFromClass R.string.ui_error_timeout
        Error.General.NotImplemented -> componentType buildFromClass R.string.ui_error_not_implemented

        None.Empty -> componentType buildFromClass R.string.ui_event_none

        Success.Empty -> componentType buildFromClass R.string.ui_event_none
        is Success.Message -> this.message buildFromMessage componentType
        Success.Auth.SignIn -> componentType buildFromClass R.string.ui_success_sign_in
    }
}

infix fun ComponentType.buildFromClass(
    @StringRes messageRes: Int
) = UiEvent(
    message = EMPTY_VALUE,
    messageResId = messageRes,
    componentType = this
)

infix fun String.buildFromMessage(
    componentType: ComponentType
) = UiEvent(
    message = this,
    messageResId = null,
    componentType = componentType
)