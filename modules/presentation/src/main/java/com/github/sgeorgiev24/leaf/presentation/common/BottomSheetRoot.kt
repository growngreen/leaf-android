package com.github.sgeorgiev24.leaf.presentation.common

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.loaders.CircularIndeterminateProgressBar
import com.github.sgeorgiev24.leaf.presentation.common.components.text.LeafTextLink
import com.github.sgeorgiev24.leaf.ui.theme.Colors
import com.github.sgeorgiev24.leaf.ui.theme.Dimens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun <ScreenState, Action, Event, ViewModel : BaseViewModel<ScreenState, Action, Event>>
BottomSheetRoot(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    viewModel: ViewModel,
    title: String = "",
    isVisible: Boolean = false,
    linkHeader: String = "",
    isLinkVisible: Boolean = false,
    onLinkHeaderCLick: () -> Unit = {},
    content: @Composable () -> Unit,
    @DrawableRes closeIconRes: Int = R.drawable.ic_close,
    closeIconColor: Color = Color.Unspecified,
    bottomContent: @Composable (() -> Unit)? = null
) {
    val isLoading by viewModel.isLoading
    val scaffoldState = rememberScaffoldState()
    val snackBarHostState = SnackbarHostState()
    val queue by viewModel.queue

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Colors.background)
    ) {

        val contentSidePadding = Dimens.padding_large
        val iconButtonSize = 36.dp
        val iconSize = 18.dp
        val rowSidePadding = contentSidePadding - ((iconButtonSize / 2) - (iconSize / 2))
        val requiredRowTopPadding = Dimens.padding_large
        val rowTopPadding = requiredRowTopPadding - ((iconButtonSize / 2) - (iconSize / 2))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = rowSidePadding,
                    top = rowTopPadding,
                    end = rowSidePadding
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(iconButtonSize),
                onClick = onCloseClick
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(closeIconRes),
                    tint = closeIconColor,
                    contentDescription = null
                )
            }
            if (isLinkVisible) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    LeafTextLink(
                        modifier = Modifier
                            .padding(bottom = Dimens.padding_small, end = Dimens.padding_small),
                        title = linkHeader
                    ) {
                        onLinkHeaderCLick()
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(visible = isVisible) {
                    Text(
                        text = title,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.size(56.dp))
        }

        val requiredContentTopPadding = Dimens.padding_large
        val contentTopPadding = requiredContentTopPadding - ((iconButtonSize - iconSize) / 2)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { }
                .wrapContentHeight()
                .padding(
                    start = contentSidePadding,
                    top = contentTopPadding,
                    end = contentSidePadding,
                    bottom = 0.dp
                )
        ) {
            content()
            UiNotificationRoot<ScreenState, Action, Event, ViewModel>(
                scaffoldState = scaffoldState,
                snackBarHostState = snackBarHostState,
                onNotificationActionPerformed = viewModel::removeHeadMessage,
                queue = queue
            )
            if (isLoading) {
                CircularIndeterminateProgressBar()
            }
        }
        if (bottomContent != null) {
            Scaffold(
                modifier
                    .height(100.dp)
                    .zIndex(1f),
                scaffoldState = scaffoldState
            ) {
                bottomContent.invoke()
            }
        }
    }
}