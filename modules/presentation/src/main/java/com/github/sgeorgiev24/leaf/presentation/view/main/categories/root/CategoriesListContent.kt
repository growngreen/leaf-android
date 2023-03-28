package com.github.sgeorgiev24.leaf.presentation.view.main.categories

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.github.sgeorgiev24.leaf.model.category.add.Category
import com.github.sgeorgiev24.leaf.presentation.common.components.util.HeightSpacer
import com.github.sgeorgiev24.leaf.presentation.common.components.util.WidthSpacer
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.mvi.EditCategoriesAction
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.root.mvi.EditCategoriesState
import com.github.sgeorgiev24.leaf.ui.theme.Dimens
import com.github.sgeorgiev24.leaf.ui.theme.GreySuit
import com.github.sgeorgiev24.leaf.ui.theme.Platinum
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesListContent(
    state: EditCategoriesState,
    action: (EditCategoriesAction) -> Unit
) {
    LazyColumn {
        items(items = state.categories, key = Category::id) { category ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement()
            ) {
                CategoryItem(
                    modifier = Modifier.align(Alignment.CenterStart),
                    category = category,
                    isRevealed = state.revealedCategory == category,
                    onCollapse = { action(EditCategoriesAction.CollapseCategory(it)) },
                    onExpand = { action(EditCategoriesAction.ExpandCategory(it)) }
                )
                AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    visible = state.revealedCategory == category,
                    enter = fadeIn(tween(500)),
                    exit = fadeOut(tween(500))
                ) {
                    CategoryActions(
                        category = category,
                        deleteCategory = {
                            action(EditCategoriesAction.OnDeleteCategoryClick(it))
                        }
                    )
                }
            }
            HeightSpacer()
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    isRevealed: Boolean,
    onCollapse: (Category) -> Unit = {},
    onExpand: (Category) -> Unit = {}
) {
    val offsetX by remember { mutableStateOf(0f) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState)
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 500) },
        targetValueByState = { if (isRevealed) -Dimens.swipeToRevealOffset.value else 0f }
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .offset { IntOffset((offsetTransition + offsetX).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        dragAmount >= 6 -> onCollapse(category)
                        dragAmount < -6 -> onExpand(category)
                    }
                }
            }
            .background(
                color = Color.White,
                shape = RoundedCornerShape(Dimens.padding_large)
            )
            .padding(Dimens.padding_medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = Platinum,
                    shape = RoundedCornerShape(percent = 50)
                )
                .padding(Dimens.padding_medium)
                .aspectRatio(1f),
            model = category.icon,
            contentDescription = null
        )
        WidthSpacer()

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = category.title,
                fontSize = 16.sp
            )
            Text(
                text = category.type.name.lowercase(),
                fontSize = 14.sp,
                color = GreySuit
            )
        }
    }
}

@Composable
fun CategoryActions(
    modifier: Modifier = Modifier,
    category: Category,
    deleteCategory: (String) -> Unit
) {
    Row(
        modifier = modifier
            .height(45.dp)
            .padding(end = Dimens.padding_medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .size(30.dp),
            onClick = { }
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        }
        WidthSpacer(width = Dimens.padding_large)
        IconButton(
            modifier = Modifier
                .size(30.dp),
            onClick = {
                deleteCategory(category.id)
            }
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}