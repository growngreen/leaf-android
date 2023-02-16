package com.github.sgeorgiev24.leaf.presentation.common.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun LeafTextLink(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    style: TextStyle? = null,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = onClick
        ),
        text = title,
        style = style ?: LocalTextStyle.current,
        color = Color.Blue
    )
}