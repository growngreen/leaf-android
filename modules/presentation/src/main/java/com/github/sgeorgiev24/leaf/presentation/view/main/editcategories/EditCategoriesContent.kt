package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data.EditCategoriesTab
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.preview.PreviewComposable
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.Platinum
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LeafScreenTitle(text = stringResource(id = R.string.edit_categories_title))
            HeightSpacer(height = Dimens.padding_large)

            TabBar(
                onCategoriesListClick = {
                    action(EditCategoriesAction.OnCategoryTabClick(EditCategoriesTab.CATEGORIES_LIST))
                },
                onAddCategoryClick = {
                    action(EditCategoriesAction.OnCategoryTabClick(EditCategoriesTab.ADD_CATEGORY))
                }
            )
            HeightSpacer(height = Dimens.padding_large)

            when (state.selectedTab) {
                EditCategoriesTab.CATEGORIES_LIST -> {
                    CategoriesListContent(
                        state = state,
                        action = action
                    )
                }
                EditCategoriesTab.ADD_CATEGORY -> {
                    AddCategoryContent(
                        state = state,
                        action = action
                    )
                }
            }
        }
    }
}

@Composable
private fun TabBar(
    onCategoriesListClick: () -> Unit,
    onAddCategoryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Platinum,
                shape = RoundedCornerShape(Dimens.padding_medium)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(.5f)
                .clickable { onCategoriesListClick() }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = Dimens.padding_medium),
                text = stringResource(id = R.string.edit_categories_my_categories)
            )
        }
        Box(
            modifier = Modifier
                .weight(.5f)
                .clickable { onAddCategoryClick() }
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = Dimens.padding_medium),
                text = stringResource(id = R.string.edit_categories_add_category_title)
            )
        }
    }
}

@Preview
@Composable
fun EditCategoriesPreview() {
    PreviewComposable {
        EditCategoriesContent(
            state = EditCategoriesState(),
            action = {}
        )
    }
}