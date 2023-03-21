package com.github.sgeorgiev24.leaf.presentation.common.components.menu

import androidx.annotation.DrawableRes
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.sgeorgiev24.leaf.presentation.R

@Composable
fun LeafContextMenu(
    modifier: Modifier = Modifier,
    options: List<Pair<String, () -> Unit>>,
    icon: @Composable () -> Unit,
    @DrawableRes iconResId: Int = R.drawable.ic_show_more
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        modifier = modifier,
        onClick = { expanded = true }
    ) {
        icon()

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    onClick = {
                        it.second()
                        expanded = false
                    },
                    content = {
                        Text(text = it.first)
                    }
                )
            }
        }
    }
}