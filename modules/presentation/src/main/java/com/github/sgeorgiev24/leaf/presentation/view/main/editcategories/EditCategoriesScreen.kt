package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.github.sgeorgiev24.leaf.presentation.common.BottomSheetRoot
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesViewModel
import com.github.sgeorgiev24.leaf.ui.lifecycle.ObserverLifecycleEvents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditCategoriesScreen() {
    val viewModel = hiltViewModel<EditCategoriesViewModel>()
    val state by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    HandleScreenEvents(viewModel)

    ObserverLifecycleEvents(
        onResume = {
            coroutineScope.launch {
                viewModel.getAllCategories()
            }
        }
    )

    BottomSheetRoot(
        modifier = Modifier.fillMaxHeight(.95f),
        viewModel = viewModel,
        onCloseClick = { viewModel.submitAction(EditCategoriesAction.OnBack) },
        content = {
            EditCategoriesContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}

@Composable
private fun HandleScreenEvents(viewModel: EditCategoriesViewModel) {
    val focusManager = LocalFocusManager.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val events = remember(viewModel.events, lifecycleOwner) {
        viewModel.events.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    LaunchedEffect(Unit) {
        events.collectLatest { event ->
            when (event) {
                ScreenEvent.ClearFocus ->
                    focusManager.clearFocus()
                else -> {}
            }
        }
    }
}