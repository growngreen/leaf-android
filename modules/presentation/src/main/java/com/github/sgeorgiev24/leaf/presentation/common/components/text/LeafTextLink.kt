package com.github.sgeorgiev24.leaf.presentation.common.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.github.sgeorgiev24.leaf.theme.Colors
import com.github.sgeorgiev24.leaf.theme.Typographs

@Composable
fun LeafTextLink(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    fontSize: TextUnit? = null,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier.clickable(
            enabled = enabled,
            onClick = onClick
        ),
        text = title,
        style = Typographs.small.copy(
            textDecoration = TextDecoration.Underline,
            fontSize = fontSize ?: Typographs.small.fontSize
        ),
        color = Colors.onBackground
    )
}