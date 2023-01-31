package com.github.sgeorgiev24.leaf.presentation.common.components.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.github.sgeorgiev24.leaf.theme.Dimens

@Composable
fun DefaultSpacer(
    height: Dp = Dimens.padding_medium
) {
    Spacer(modifier = Modifier.height(height))
}