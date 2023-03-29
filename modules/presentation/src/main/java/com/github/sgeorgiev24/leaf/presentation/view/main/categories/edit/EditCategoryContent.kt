package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryAction
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.mvi.EditCategoryState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar

@Composable
fun EditCategoryContent(
    state: EditCategoryState,
    action: (EditCategoryAction) -> Unit
) {
    LeafCollapsingToolbar(
        title = stringResource(id = R.string.edit_category_title),
        scrollProvider = { 0 },
        onBack = { },
        isBackButtonShown = false
    ) {
        Column {
            LeafScreenTitle(text = stringResource(id = R.string.edit_category_title))
            HeightSpacer(height = Dimens.padding_large)
        }
    }
}