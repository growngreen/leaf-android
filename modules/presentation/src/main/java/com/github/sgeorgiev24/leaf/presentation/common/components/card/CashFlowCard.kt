package com.github.sgeorgiev24.leaf.presentation.common.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.ui.preview.DevicePreview
import com.github.sgeorgiev24.leaf.ui.theme.GreySuit
import com.onthemarket.mobile.ui.theme.Dimens
import com.onthemarket.mobile.ui.theme.Typographs

@Composable
fun CashFlowCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconResId: Int,
    value: String
) {
    Card(
        modifier = modifier
            .padding(Dimens.padding_medium)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(id = iconResId), contentDescription = null)
                Text(
                    text = value,
                    fontSize = 28.sp
                )
            }
            Text(
                text = title,
                style = Typographs.h3,
                color = GreySuit
            )
        }
    }
}

@DevicePreview
@Composable
fun CashFlowCardPreview() {
    CashFlowCard(title = "CashFlow", iconResId = R.drawable.ic_income, value = "1914")
}