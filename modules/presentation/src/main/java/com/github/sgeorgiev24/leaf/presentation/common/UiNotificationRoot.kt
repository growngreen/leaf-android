package com.github.sgeorgiev24.leaf.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.sgeorgiev24.leaf.model.util.Queue
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.model.ComponentType
import com.github.sgeorgiev24.leaf.presentation.model.UiEvent
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun <ScreenState, Action, Event, ViewModel : BaseViewModel<ScreenState, Action, Event>>
UiNotificationRoot(
    scaffoldState: ScaffoldState,
    snackBarHostState: SnackbarHostState,
    onNotificationActionPerformed: () -> Unit,
    queue: Queue<UiEvent>
) {
    val coroutineScope = rememberCoroutineScope()

    val onShowSnackbar: (String, String?, action: (() -> Unit)?) -> Unit =
        { title, actionLabel, action ->
            coroutineScope.launch {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = title,
                    actionLabel = actionLabel,
                    duration = SnackbarDuration.Short
                )
                when (snackbarResult) {
                    SnackbarResult.Dismissed -> {
                        action?.invoke()
                        onNotificationActionPerformed()
                        Timber.i("Snackbar dismissed.")
                    }
                    SnackbarResult.ActionPerformed -> {
                        action?.invoke()
                        onNotificationActionPerformed()
                        Timber.i("Snackbar action performed.")
                    }
                }
            }
        }

    val onShowNotificationSnackbar: ((String, String?, action: (() -> Unit)?) -> Unit) =
        { title, actionLabel, _ ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(title, actionLabel)
            }
        }

    if (!queue.isEmpty()) {
        queue.peek()?.let { response ->
            val message =
                response.messageResId?.let { stringResource(id = it) } ?: response.message

            when (response.componentType) {
                is ComponentType.SnackBarNotification -> {
                    onShowNotificationSnackbar("", "") {}
                    SnackBarHost(event = response, snackBarHostState = snackBarHostState)
                }
                ComponentType.Dialog -> {} // TODO
                ComponentType.None -> {}
                is ComponentType.SnackBar -> {
                    val actionLabel =
                        resolveActionLabel(response.componentType)

                    onShowSnackbar(
                        message,
                        actionLabel,
                        response.componentType.dismiss ?: response.componentType.undo
                    )
                }
                ComponentType.Toast -> {
                    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun resolveActionLabel(uiComponentType: ComponentType.SnackBar): String? {
    return when {
        uiComponentType.undo != null -> {
            stringResource(id = R.string.undo_action)
        }
        uiComponentType.dismiss != null -> {
            stringResource(id = R.string.dismiss_action)
        }
        else -> null
    }
}

@Composable
private fun SnackBarHost(
    event: UiEvent?,
    snackBarHostState: SnackbarHostState
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 100.dp),
        verticalAlignment = Alignment.Top
    ) {
        SnackbarHost(
            modifier = Modifier
                .navigationBarsPadding()
                .align(Alignment.Top),
            hostState = snackBarHostState,
            snackbar = {
                SnackBarNotification(event = event)
            }
        )
    }
}

@Composable
fun SnackBarNotification(
    event: UiEvent?,
) {
    Snackbar(
        backgroundColor = Color.LightGray, // TODO change when colors are available
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
//            Image(
//                modifier = Modifier
//                    .size(32.dp)
//                    .clip(CircleShape),
//                // TODO change when resources are available
//                painter = painterResource(id = R.drawable),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                event?.message?.let { title ->
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
