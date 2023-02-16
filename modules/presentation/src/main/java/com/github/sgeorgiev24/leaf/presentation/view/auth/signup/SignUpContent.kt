package com.github.sgeorgiev24.leaf.presentation.view.auth.signup

import androidx.compose.runtime.Composable
import com.github.sgeorgiev24.leaf.presentation.common.components.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpAction
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpState

@Composable
fun SignUpContent(
    state: SignUpState,
    action: (SignUpAction) -> Unit,
) {
    LeafScreenTitle(text = "Sign Up")
}