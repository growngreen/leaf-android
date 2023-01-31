package com.github.sgeorgiev24.leaf.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val padding_extra_small: Dp,
    val padding_small: Dp,
    val padding_medium: Dp,
    val padding_large: Dp,
    val padding_extra_large: Dp,
    val padding_huge: Dp,
    val padding_horizontal: Dp,
    val padding_vertical: Dp
)

val smallDimensions = Dimensions(
    padding_extra_small = 1.dp,
    padding_small = 2.dp,
    padding_medium = 4.dp,
    padding_large = 8.dp,
    padding_extra_large = 16.dp,
    padding_huge = 32.dp,
    padding_horizontal = 16.dp,
    padding_vertical = 16.dp
)

val sw360Dimensions = Dimensions(
    padding_extra_small = 2.dp,
    padding_small = 4.dp,
    padding_medium = 8.dp,
    padding_large = 16.dp,
    padding_extra_large = 32.dp,
    padding_huge = 64.dp,
    padding_horizontal = 20.dp,
    padding_vertical = 20.dp
)

val sw600Dimensions = Dimensions(
    padding_extra_small = 4.dp,
    padding_small = 8.dp,
    padding_medium = 16.dp,
    padding_large = 32.dp,
    padding_extra_large = 64.dp,
    padding_huge = 128.dp,
    padding_horizontal = 24.dp,
    padding_vertical = 24.dp
)

val sw940Dimensions = Dimensions(
    padding_extra_small = 8.dp,
    padding_small = 16.dp,
    padding_medium = 32.dp,
    padding_large = 64.dp,
    padding_extra_large = 128.dp,
    padding_huge = 256.dp,
    padding_horizontal = 28.dp,
    padding_vertical = 28.dp
)