package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.sgeorgiev24.leaf.presentation.common.BottomSheetRoot
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryAction
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryViewModel

@Composable
fun EditCategoryScreen() {
    val viewModel = hiltViewModel<EditCategoryViewModel>()
    val state by viewModel.state.collectAsState()

    BottomSheetRoot(
        modifier = Modifier.wrapContentHeight(),
        viewModel = viewModel,
        onCloseClick = { viewModel.submitAction(EditCategoryAction.OnBackClick) },
        content = {
            EditCategoryContent(
                state = state,
                action = { viewModel.submitAction(it) }
            )
        }
    )
}