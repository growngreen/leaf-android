package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.github.sgeorgiev24.leaf.ui.theme.LeafTheme

@Composable
fun BottomBarLabel(
    @StringRes labelResId: Int,
    isSelected: Boolean
) {
    Text(
        text = stringResource(labelResId),
        style = if (isSelected) LeafTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold) else LeafTheme.typography.bodySmall,
        maxLines = 1
    )
}