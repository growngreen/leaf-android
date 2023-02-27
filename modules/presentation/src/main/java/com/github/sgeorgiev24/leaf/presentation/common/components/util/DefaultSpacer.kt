package com.github.sgeorgiev24.leaf.presentation.common.components.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.onthemarket.mobile.ui.theme.Dimens

@Composable
fun HeightSpacer(
    height: Dp = Dimens.padding_medium
) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WidthSpacer(
    width: Dp = Dimens.padding_medium
) {
    Spacer(modifier = Modifier.width(width))
}