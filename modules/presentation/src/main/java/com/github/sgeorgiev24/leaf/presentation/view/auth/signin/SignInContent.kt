package com.github.sgeorgiev24.leaf.presentation.view.auth.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi.SignInAction
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi.SignInState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.onthemarket.mobile.ui.theme.Dimens
import com.onthemarket.mobile.ui.theme.Typographs

@Composable
fun SignInContent(
    state: SignInState,
    action: (SignInAction) -> Unit
) {
    LeafCollapsingToolbar(
        title = stringResource(R.string.sign_up_title),
        scrollProvider = { 0 },
        onBack = { action(SignInAction.OnBack) }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(
                        start = Dimens.padding_horizontal,
                        end = Dimens.padding_horizontal,
                        top = Dimens.padding_vertical
                    )
            ) {
                LeafScreenTitle(textResId = R.string.sign_in_title)
                HeightSpacer(height = Dimens.padding_vertical)

                LeafOutlinedTextField(
                    inputWrapper = state.email,
                    label = stringResource(R.string.sign_in_email),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignInAction.OnNextActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                    visualTransformation = VisualTransformation.None,
                    onTextChanged = { text, _ ->
                        action(SignInAction.OnEmailValueChange(text))
                    },
                )
                HeightSpacer()

                LeafOutlinedTextField(
                    inputWrapper = state.password,
                    label = stringResource(R.string.sign_in_password),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action(SignInAction.OnDoneActionClick)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChanged = { text, _ ->
                        action(SignInAction.OnPasswordValueChange(text))
                    },
                )
                HeightSpacer()

                LeafButton(
                    titleResId = R.string.sign_in_button,
                    enabled = state.isSignInButtonEnabled,
                    onClick = { action(SignInAction.OnSignInClick) }
                )
                HeightSpacer()

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisAlignment = FlowMainAxisAlignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 2.dp),
                        text = stringResource(R.string.sign_in_dont_have_an_account),
                        style = Typographs.body1
                    )
                    LeafTextLink(
                        Modifier.weight(1f),
                        title = stringResource(R.string.sign_in_sign_up_link),
                        onClick = { action(SignInAction.OnSignUpLinkClick) },
                        style = Typographs.body1Bold
                    )
                }
            }
        }
    }
}