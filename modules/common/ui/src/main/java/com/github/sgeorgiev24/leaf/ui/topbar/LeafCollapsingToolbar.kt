package com.github.sgeorgiev24.leaf.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.sgeorgiev24.leaf.ui.R
import com.github.sgeorgiev24.leaf.ui.theme.GhostWhite
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.Typographs

@Composable
fun LeafCollapsingToolbar(
    title: String,
    isBackButtonShown: Boolean = true,
    scrollProvider: () -> Int,
    onBack: () -> Unit,
    isLinkVisible: Boolean = false,
    linkTitle: String = "",
    onLinkClick: () -> Unit = {},
    actionButton: (@Composable () -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val collapseRange = with(LocalDensity.current) { (200.dp - 100.dp).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    val isVisible by remember {
        derivedStateOf {
            collapseFractionProvider() >= 0.5f
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth().background(GhostWhite),
    ) {
        content()

        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isBackButtonShown) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            top = Dimens.padding_large,
                            start = Dimens.padding_medium
                        ),
                    onClick = { onBack() }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
//                        tint = Colors.otmAppBarIconsTint
                    )
                }
            }
            AnimatedVisibility(visible = isLinkVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = { onLinkClick() }
                        ),
                        text = linkTitle,
                        style = Typographs.body1.copy(
                            textDecoration = TextDecoration.Underline,
//                            color = Colors.otmLinkTextColor
                        )
                    )
                }
            }
            // TODO here if the text is too wide it could overlap the icons, but lets not worry about it now
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(visible = isVisible) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
//                        color = Colors.otmScreenTitleColor
                    )
                }
            }
            actionButton?.let { action ->
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                        .height(56.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    action()
                }
            } ?: Spacer(modifier = Modifier.size(56.dp))
        }
    }
}