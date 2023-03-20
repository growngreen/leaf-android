package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.LeafOutlinedTextField
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.text.LeafScreenTitle
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
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
        Column {
            LeafScreenTitle(text = stringResource(id = R.string.edit_categories_title))

            LeafOutlinedTextField(
                inputWrapper = state.categoryName,
                label = stringResource(R.string.edit_categories_category_name),
                keyboardActions = KeyboardActions(
                    onDone = {
                        action(EditCategoriesAction.OnDoneActionClick)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = VisualTransformation.None,
                onTextChanged = { text, _ ->
                    action(EditCategoriesAction.OnCategoryNameValueChange(text))
                },
            )
            HeightSpacer()

            HeightSpacer(height = Dimens.padding_huge)
        }
        // TODO: add dropdown for category type
        // TODO: add list of all categories by category type
    }
}