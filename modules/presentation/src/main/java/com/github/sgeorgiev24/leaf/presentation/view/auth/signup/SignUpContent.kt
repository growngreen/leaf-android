package com.github.sgeorgiev24.leaf.presentation.view.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.button.LeafButton
import com.github.sgeorgiev24.leaf.presentation.common.components.text.LeafTextLink
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.LeafOutlinedTextField
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpAction
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpState
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.onthemarket.mobile.ui.theme.Dimens
import com.onthemarket.mobile.ui.theme.Typographs

@Composable
fun SignUpContent(
    state: SignUpState,
    action: (SignUpAction) -> Unit
) {
    val scrollState = rememberScrollState()

    LeafCollapsingToolbar(
        title = stringResource(R.string.sign_up_title),
        scrollProvider = { scrollState.value },
        onBack = { action(SignUpAction.OnBack) },
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
                    .weight(1f)
                    .padding(
                        start = Dimens.padding_horizontal,
                        end = Dimens.padding_horizontal,
                        top = Dimens.padding_vertical
                    )
            ) {
                LeafScreenTitle(textResId = R.string.sign_up_title)
                HeightSpacer(height = Dimens.padding_vertical)

                LeafOutlinedTextField(
                    inputWrapper = state.name,
                    label = stringResource(R.string.sign_up_full_name),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignUpAction.OnNextActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    visualTransformation = VisualTransformation.None,
                    onTextChanged = { text, _ ->
                        action(SignUpAction.OnNameValueChange(text))
                    },
                )
                HeightSpacer()

                LeafOutlinedTextField(
                    inputWrapper = state.email,
                    label = stringResource(R.string.sign_up_email),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignUpAction.OnNextActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                    visualTransformation = VisualTransformation.None,
                    onTextChanged = { text, _ ->
                        action(SignUpAction.OnEmailValueChange(text))
                    },
                )
                HeightSpacer()

                LeafOutlinedTextField(
                    inputWrapper = state.password,
                    label = stringResource(R.string.sign_up_password),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignUpAction.OnNextActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChanged = { text, _ ->
                        action(SignUpAction.OnPasswordValueChange(text))
                    },
                )
                HeightSpacer()

                LeafOutlinedTextField(
                    inputWrapper = state.confirmPassword,
                    label = stringResource(R.string.sign_up_confirm_password),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignUpAction.OnDoneActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChanged = { text, _ ->
                        action(SignUpAction.OnConfirmPasswordValueChange(text))
                    },
                )
                HeightSpacer(height = Dimens.padding_extra_large)

                LeafButton(
                    titleResId = R.string.sign_up_button,
                    enabled = state.isSignUpButtonEnabled,
                    onClick = { action(SignUpAction.OnSignUpClick) }
                )
                HeightSpacer()

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisAlignment = FlowMainAxisAlignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 2.dp),
                        text = stringResource(R.string.sign_up_got_an_account),
                        style = Typographs.body1
                    )
                    LeafTextLink(
                        Modifier.weight(1f),
                        title = stringResource(R.string.sign_up_sign_in_link),
                        onClick = { action(SignUpAction.OnSignInLinkClick) },
                        style = Typographs.body1Bold
                    )
                }
            }
        }
    }
}