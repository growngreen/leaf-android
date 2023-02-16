package com.github.sgeorgiev24.leaf.presentation.common.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.github.sgeorgiev24.leaf.ui.theme.Colors
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.Typographs

@Composable
fun LeafButton(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit,
    textStyle: TextStyle = Typographs.button1,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .wrapContentWidth()
                .padding(vertical = Dimens.padding_small),
            textAlign = TextAlign.Center,
            style = textStyle,
            text = stringResource(id = titleResId),
            color = Colors.onSecondary
        )
    }
}