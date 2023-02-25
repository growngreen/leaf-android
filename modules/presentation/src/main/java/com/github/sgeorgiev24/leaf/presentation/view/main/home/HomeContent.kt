package com.github.sgeorgiev24.leaf.presentation.view.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.LeafDropdownMenu
import com.github.sgeorgiev24.leaf.presentation.common.components.icon.ShowMoreIcon
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeAction
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar
import com.onthemarket.mobile.ui.theme.Dimens

@Composable
fun HomeContent(
    state: HomeState,
    action: (HomeAction) -> Unit
) {
    val screenTitle = state.user?.name?.let {
        stringResource(id = R.string.home_title_user_name, it)
    } ?: stringResource(id = R.string.home_title)

    LeafCollapsingToolbar(
        title = screenTitle,
        scrollProvider = { 0 },
        onBack = { },
        isBackButtonShown = false
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
                        top = Dimens.padding_large
                    )
            ) {
                ShowMore(
                    onSignOutClick = { action(HomeAction.OnSignOutClick) }
                )

                LeafScreenTitle(text = screenTitle)
            }
        }
    }
}

@Composable
private fun ShowMore(
    onSignOutClick: () -> Unit
) {
    val signOutOption = Pair(
        stringResource(id = R.string.home_dropdown_sign_out),
        onSignOutClick
    )
    val options = listOf(signOutOption)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        LeafDropdownMenu(
            icon = {
                ShowMoreIcon()
            },
            options = options
        )
    }
}