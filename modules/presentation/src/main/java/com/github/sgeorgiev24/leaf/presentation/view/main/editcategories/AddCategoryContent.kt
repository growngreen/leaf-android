package com.github.sgeorgiev24.leaf.presentation.view.main.editcategories

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.presentation.common.components.button.LeafButton
import com.github.sgeorgiev24.leaf.presentation.common.components.menu.LeafDropDownMenu
import com.github.sgeorgiev24.leaf.presentation.common.components.textfield.LeafOutlinedTextField
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.data.CategoryTypeOption
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.editcategories.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.preview.PreviewComposable
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.Platinum
import com.github.sgeorgiev24.leaf.ui.theme.Typographs

@Composable
fun AddCategoryContent(
    state: EditCategoriesState,
    action: (EditCategoriesAction) -> Unit
) {
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

    CategoryTypeDropdown(
        categoryTypeOptions = state.categoryTypeOptions,
        placeholderResId = state.selectedCategoryTypeOption?.titleResId
            ?: R.string.dropdown_menu_default_placeholder,
        onOptionChange = { uuid ->
            action(EditCategoriesAction.OnCategoryTypeOptionSelected(uuid))
        }
    )

    AddCategoryButton()

    CategoryIcons(icons = state.categoryIcons)
}

@Composable
private fun CategoryTypeDropdown(
    categoryTypeOptions: List<CategoryTypeOption>,
    @StringRes placeholderResId: Int,
    onOptionChange: (String) -> Unit
) {
    LeafDropDownMenu(
        labelResId = R.string.edit_categories_select_type,
        options = categoryTypeOptions,
        onOptionChange = { option ->
            onOptionChange(option.uuid)
        },
        placeholderResId = placeholderResId
    )
    HeightSpacer()
}

@Composable
private fun AddCategoryButton() {
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
}

@Composable
private fun CategoryIcons(
    icons: List<String>
) {
    Text(
        text = stringResource(R.string.edit_categories_select_icon),
        style = Typographs.smallBold
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(45.dp),
        contentPadding = PaddingValues(Dimens.padding_medium),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding_medium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.padding_medium)
    ) {
        items(icons) {
            Box(
                modifier = Modifier
                    .background(
                        color = Platinum,
                        shape = RoundedCornerShape(percent = 50)
                    )
                    .padding(Dimens.padding_medium)
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f),
                    model = it,
                    contentDescription = null
                )
            }
        }
    }
    HeightSpacer()
}

@Preview
@Composable
fun AddCategoryPreview() {
    PreviewComposable {
        AddCategoryContent(
            state = EditCategoriesState(),
            action = {}
        )
    }
}