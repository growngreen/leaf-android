package com.github.sgeorgiev24.leaf.presentation.view.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpAction
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.mvi.SignUpState
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar

@Composable
fun SignUpContent(
    state: SignUpState,
    action: (SignUpAction) -> Unit,
) {
    val scrollState = rememberScrollState()

    LeafCollapsingToolbar(
        title = "Sign Up",
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
                LeafScreenTitle(text = "Sign Up")
            }
        }
    }
}