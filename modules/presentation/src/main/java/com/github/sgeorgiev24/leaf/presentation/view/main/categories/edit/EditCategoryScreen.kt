package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.BottomSheetRoot
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.ScreenEvent
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryAction
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditCategoryScreen() {
    val viewModel = hiltViewModel<EditCategoryViewModel>()
    val state by viewModel.state.collectAsState()

    HandleScreenEvents(viewModel)

    BottomSheetRoot(
        modifier = Modifier.fillMaxHeight(.95f),
        viewModel = viewModel,
        onCloseClick = { viewModel.submitAction(EditCategoryAction.OnBackClick) },
        content = {
            EditCategoryContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        },
        isLinkVisible = true,
        linkHeader = stringResource(id = R.string.edit_category_save),
        onLinkHeaderCLick = { viewModel.submitAction(EditCategoryAction.OnSaveClick) }
    )
}

@Composable
private fun HandleScreenEvents(viewModel: EditCategoryViewModel) {
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