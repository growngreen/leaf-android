package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.button.LeafButton
import com.github.sgeorgiev24.leaf.presentation.common.components.menu.LeafDropDownMenu
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.LeafOutlinedTextField
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.preview.PreviewComposable
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

            LeafDropDownMenu(
                labelResId = R.string.edit_categories_select_type,
                options = state.categoryTypeOptions,
                onOptionChange = { option ->
                    action(EditCategoriesAction.OnCategoryTypeOptionSelected(option.uuid))
                },
                placeholderResId = state.selectedCategoryTypeOption?.titleResId
                    ?: R.string.dropdown_menu_default_placeholder
            )
            HeightSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                LeafButton(
                    modifier = Modifier
                        .fillMaxWidth(.5f),
                    titleResId = R.string.edit_categories_add_category,
                    onClick = { }
                )
            }

            HeightSpacer(height = Dimens.padding_huge)
        }
        // TODO: add dropdown for category type
        // TODO: add list of all categories by category type
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