package com.github.sgeorgiev24.leaf.presentation.view.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.sgeorgiev24.leaf.model.auth.LeafUser
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.LeafDropdownMenu
import com.github.sgeorgiev24.leaf.presentation.common.components.card.CashFlowCard
import com.github.sgeorgiev24.leaf.presentation.common.components.icon.ShowMoreIcon
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.common.components.util.WidthSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeAction
import com.github.sgeorgiev24.leaf.presentation.view.main.home.mvi.HomeState
import com.github.sgeorgiev24.leaf.ui.preview.DevicePreview
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.theme.Cinnabar
import com.github.sgeorgiev24.leaf.ui.theme.Pantone
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar
import com.github.sgeorgiev24.leaf.ui.theme.Colors
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.Typographs

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
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = Dimens.padding_horizontal,
                    end = Dimens.padding_horizontal,
                    top = Dimens.padding_large
                )
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                TopComponents(
                    onSignOutClick = { action(HomeAction.OnSignOutClick) },
                    screenTitle = screenTitle
                )
                BottomButtons(
                    onEditCategoriesClick = { action(HomeAction.OnEditCategoriesClick) }
                )
            }
        }
    }
}

@Composable
private fun BoxScope.TopComponents(
    onSignOutClick: () -> Unit,
    screenTitle: String
) {
    Column(
        modifier = Modifier.Companion
            .align(Alignment.TopCenter)
    ) {
        ShowMore(
            onSignOutClick = onSignOutClick
        )

        LeafScreenTitle(text = screenTitle)

        CashFlowCards(
            income = "1914",
            expenses = "19.14",
            balance = "1894.86"
        )
    }
}

@Composable
private fun BoxScope.BottomButtons(
    onEditCategoriesClick: () -> Unit
) {
    Row(
        modifier = Modifier.Companion
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(bottom = Dimens.padding_large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ExtendedFloatingActionButton(
            shape = RoundedCornerShape(
                size = Dimens.extended_fab_corner_shape
            ),
            containerColor = Colors.buttonContainerColor,
            onClick = {},
            content = {
                Icon(
                    modifier = Modifier
                        .size(23.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = Color.White
                )
                WidthSpacer(width = Dimens.padding_small)
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentWidth()
                        .padding(vertical = Dimens.padding_small),
                    textAlign = TextAlign.Center,
                    style = Typographs.button1,
                    text = stringResource(id = R.string.home_add_cash_flow),
                    color = Colors.fabTextColor
                )
            },
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        )

        ExtendedFloatingActionButton(
            shape = RoundedCornerShape(
                size = Dimens.extended_fab_corner_shape
            ),
            containerColor = Colors.buttonContainerColor,
            onClick = onEditCategoriesClick,
            content = {
                Text(
                    text = stringResource(R.string.home_edit_categories),
                    style = Typographs.h3,
                    color = Colors.fabTextColor
                )
            },
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        )
    }
}

@Composable
private fun CashFlowCards(
    income: String,
    expenses: String,
    balance: String
) {
    HeightSpacer(height = Dimens.padding_large)
    Row {
        CashFlowCard(
            modifier = Modifier.weight(.5f),
            title = stringResource(R.string.home_cashflow_income),
            iconResId = R.drawable.ic_income,
            value = income,
            valueTextColor = Pantone
        )
        WidthSpacer(width = Dimens.padding_large)
        CashFlowCard(
            modifier = Modifier.weight(.5f),
            title = stringResource(R.string.home_cashflow_expenses),
            iconResId = R.drawable.ic_expenses,
            value = expenses,
            valueTextColor = Cinnabar
        )
    }
    HeightSpacer(height = Dimens.padding_large)
    CashFlowCard(
        title = stringResource(R.string.home_cashflow_balance),
        iconResId = R.drawable.ic_balance,
        value = balance
    )
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

@DevicePreview
@Composable
fun HomePreview() {
    HomeContent(
        state = HomeState(LeafUser(null, "Siso")),
        action = {}
    )
}