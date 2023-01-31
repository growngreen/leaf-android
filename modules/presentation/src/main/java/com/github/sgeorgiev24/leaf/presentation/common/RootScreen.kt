package com.github.sgeorgiev24.leaf.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.github.sgeorgiev24.leaf.presentation.common.components.loaders.CircularIndeterminateProgressBar
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun <ScreenState, Action, Event, ViewModel : BaseViewModel<ScreenState, Action, Event>>
RootScreen(
    viewModel: ViewModel,
    eventProcessor: ((event: Event) -> Unit)? = null,
    content: @Composable () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val snackBarHostState = SnackbarHostState()
    val queue by viewModel.queue
    val isLoading by viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { eventProcessor?.invoke(it) }
    }

    Scaffold(
        backgroundColor = Color.Transparent,
        scaffoldState = scaffoldState
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
}
