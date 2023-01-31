package com.github.sgeorgiev24.leaf.presentation.common.components.textfield

import androidx.compose.ui.focus.FocusDirection

sealed class ScreenEvent {
    object ClearFocus : ScreenEvent()
    class RequestFocus(val textFieldKey: FocusedTextFieldKey) : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
}