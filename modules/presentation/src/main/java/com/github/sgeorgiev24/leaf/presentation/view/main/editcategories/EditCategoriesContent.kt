package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.topbar.LeafCollapsingToolbar

@Composable
fun EditCategoriesContent(
    state: EditCategoriesState,
    action: (EditCategoriesAction) -> Unit
) {
    LeafCollapsingToolbar(
        title = stringResource(id = R.string.edit_categories_title),
        scrollProvider = { 0 },
        onBack = { },
        isBackButtonShown = false
    ) {
        LeafScreenTitle(text = stringResource(id = R.string.edit_categories_title))

        // TODO: add option to add new category
        // TODO: add dropdown for category type
        // TODO: add list of all categories by category type
    }
}