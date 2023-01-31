package com.github.sgeorgiev24.leaf.presentation.common.components.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.sgeorgiev24.leaf.theme.Dimens
import com.github.sgeorgiev24.leaf.theme.Typographs

@Composable
fun LeafOutlinedTextField(
    modifier: Modifier = Modifier,
    inputWrapper: InputWrapper,
    label: String,
    maxLines: Int = 1,
    maxLength: Int = 100,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    leadingIcon: ImageVector? = null,
    trailingIconError: @Composable (() -> Unit)? = null,
    onTextChanged: ((text: String, lostFocusOnce: Boolean) -> Unit)? = null,
    onFocusChanged: ((isFocused: Boolean, lostFocusOnce: Boolean) -> Unit)? = null,
) {
    val focused = remember { mutableStateOf(false) }

    val fieldValue = remember {
        mutableStateOf(TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length)))
    }

    if (fieldValue.value.text != inputWrapper.value) {
        fieldValue.value = TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length))
    }

    val lostFocusOnce = remember { mutableStateOf(false) }

    val isError = inputWrapper.errorResId != null && !focused.value

    LeafBaseOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .testTag("TAG_TEXT_FIELD")
            .onFocusChanged {
                focused.value = it.isFocused
                if (!it.isFocused && inputWrapper.value.isNotEmpty()) lostFocusOnce.value = true
                onFocusChanged?.invoke(it.hasFocus, lostFocusOnce.value)
            },
        textFieldValue = fieldValue.value,
        label = label,
        maxLines = maxLines,
        maxLength = maxLength,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        readOnly = readOnly,
        enabled = enabled,
        isError = isError,
        errorTextResId = inputWrapper.errorResId,
        horizontalAlignment = horizontalAlignment,
        leadingIcon = leadingIcon,
        trailingIconError = trailingIconError,
        onTextChanged = {
            fieldValue.value = it
            onTextChanged?.invoke(it.text, lostFocusOnce.value)
        },
    )
}

@Composable
fun LeafBaseOutlinedTextField(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    label: String,
    maxLines: Int = 1,
    maxLength: Int = 100,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean,
    @StringRes errorTextResId: Int? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    leadingIcon: ImageVector? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingIconError: @Composable (() -> Unit)? = null,
    onTextChanged: ((textField: TextFieldValue) -> Unit)? = null,
) {

    val leadIcon: @Composable (() -> Unit)? = if (leadingIcon != null) {
        { Icon(imageVector = leadingIcon, contentDescription = null) }
    } else null

    Column(
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = modifier,
            enabled = enabled,
            value = textFieldValue,
            label = { Text(text = label) },
            onValueChange = {
                if (it.text.length <= maxLength)
                    onTextChanged?.invoke(it)
            },
            textStyle = Typographs.bodyMedium,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            isError = isError,
            maxLines = maxLines,
            leadingIcon = leadIcon,
            trailingIcon = {
                if (isError) trailingIconError?.invoke() else trailingIcon?.invoke()
            },
            readOnly = readOnly
        )

        if (isError) {
            errorTextResId?.let { textResId ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .padding(start = Dimens.padding_small)
                        .testTag("TAG_ERROR"),
                    text = stringResource(id = textResId),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}
