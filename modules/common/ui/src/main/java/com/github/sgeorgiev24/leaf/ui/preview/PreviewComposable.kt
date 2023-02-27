package com.github.sgeorgiev24.leaf.ui.preview

import androidx.compose.runtime.Composable
import com.github.sgeorgiev24.leaf.model.settings.Theme
import com.github.sgeorgiev24.leaf.ui.theme.LeafTheme

@Composable
fun PreviewComposable(
    theme: Theme = Theme.LIGHT,
    content: @Composable () -> Unit = {}
) {
    LeafTheme(theme) {
        content()
    }
}