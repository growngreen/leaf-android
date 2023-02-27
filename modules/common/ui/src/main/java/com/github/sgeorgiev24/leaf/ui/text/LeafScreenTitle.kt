package com.github.sgeorgiev24.leaf.ui.text

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.onthemarket.mobile.ui.theme.Typographs

@Composable
fun LeafScreenTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    @StringRes textResId: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(id = textResId),
        textAlign = textAlign,
        style = Typographs.displayLarge
    )
}

@Composable
fun LeafScreenTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = Typographs.displayLarge
    )
}