package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.BottomSheetRoot
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesViewModel

@Composable
fun EditCategoriesScreen() {
    val viewModel = hiltViewModel<EditCategoriesViewModel>()
    val state by viewModel.state.collectAsState()

    BottomSheetRoot(
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