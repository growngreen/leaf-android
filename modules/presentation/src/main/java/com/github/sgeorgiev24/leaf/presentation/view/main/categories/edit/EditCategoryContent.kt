package com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.LeafOutlinedTextField
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.CategoryIcons
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
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            LeafScreenTitle(text = stringResource(id = R.string.edit_category_title))
            HeightSpacer(height = Dimens.padding_large)

            LeafOutlinedTextField(
                inputWrapper = state.categoryName,
                label = stringResource(R.string.edit_category_category_name),
                keyboardActions = KeyboardActions(
                    onDone = {
                        action(EditCategoryAction.OnDoneActionClick)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = VisualTransformation.None,
                onTextChanged = { text, _ ->
                    action(EditCategoryAction.OnCategoryNameValueChange(text))
                },
            )
            HeightSpacer()

            CategoryIcons(
                icons = state.categoryIcons,
                selectedIcon = state.selectedCategoryIcon,
                onIconClick = { icon ->
                    action(EditCategoryAction.OnCategoryIconClick(icon))
                }
            )
        }
    }
}