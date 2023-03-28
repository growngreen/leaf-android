package com.github.sgeorgiev24.leaf.presentation.common.components.menu

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.model.dropdown.DropdownMenuOption
import com.github.sgeorgiev24.leaf.ui.preview.PreviewComposable
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.GreySuit
import com.github.sgeorgiev24.leaf.ui.theme.Typographs

@Composable
fun LeafDropDownMenu(
    modifier: Modifier = Modifier,
    options: List<DropdownMenuOption>,
    @StringRes labelResId: Int,
    onOptionChange: (DropdownMenuOption) -> Unit,
    expanded: Boolean = false,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    @StringRes placeholderResId: Int = R.string.dropdown_menu_default_placeholder
) {
    var expand by remember { mutableStateOf(expanded) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(labelResId),
            style = Typographs.smallBold
        )
        HeightSpacer()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Card(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(size = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(),
                            onClick = {
                                expand = true
                            }
                        )
                        .padding(horizontal = Dimens.padding_large),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val textColor =
                        if (placeholderResId == R.string.dropdown_menu_default_placeholder)
                            GreySuit
                        else
                            Color.Black
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = stringResource(placeholderResId),
                        style = Typographs.body1,
                        color = textColor
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand),
                        contentDescription = null
                    )
                }
            }
            DropdownMenu(
                expanded = expand,
                onDismissRequest = {
                    expand = false
                },
                offset = offset,
                properties = properties
            ) {
                options.forEachIndexed { index, option ->
                    if (index != 0 && index < options.size) {
                        Divider()
                    }
                    DropdownMenuItem(
                        text = { Text(stringResource(id = option.titleResId)) },
                        onClick = {
                            expand = false
                            onOptionChange(option)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.Black
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LeafDropDownMenuPreview() {
    PreviewComposable {
        LeafDropDownMenu(
            options = emptyList(),
            onOptionChange = { _ -> },
            labelResId = R.string.edit_categories_add_category
        )
    }
}