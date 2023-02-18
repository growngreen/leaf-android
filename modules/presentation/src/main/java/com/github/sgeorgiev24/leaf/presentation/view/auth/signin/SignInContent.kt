package com.github.sgeorgiev24.leaf.presentation.view.auth.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi.SignInAction
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.mvi.SignInState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar

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
            }
        }
    }
}