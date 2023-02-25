package com.github.sgeorgiev24.leaf.presentation.common.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.ui.preview.DevicePreview
import com.onthemarket.mobile.ui.theme.Colors
import com.onthemarket.mobile.ui.theme.Dimens
import com.onthemarket.mobile.ui.theme.Typographs

@Composable
fun CashFlowCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconResId: Int,
    value: String,
    valueTextColor: Color = Colors.cashFlowCardValueColor
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Colors.cashFlowCardBackgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_large),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = iconResId),
                    contentDescription = null
                )
                Text(
                    text = value,
                    fontSize = 28.sp,
                    color = valueTextColor
                )
            }
            Text(
                text = title,
                style = Typographs.h3,
                color = Colors.cashFlowCardTitleColor
            )
        }
    }
}

@DevicePreview
@Composable
fun CashFlowCardPreview() {
    CashFlowCard(title = "CashFlow", iconResId = R.drawable.ic_income, value = "1914")
}