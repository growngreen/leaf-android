package com.github.sgeorgiev24.leaf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import com.github.sgeorgiev24.leaf.model.settings.Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProvideAppColors(
    colors: LeafColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppColors provides colors, content = content)
}

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppDimens provides dimensions, content = content)
}

@Composable
fun ProvideTypography(
    typography: LeafTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalTypography provides typography, content = content)
}

@Composable
fun ProvideIsLightTheme(
    isLightTheme: Boolean,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalIsLightTheme provides isLightTheme, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf { smallDimensions }
private val LocalAppColors = staticCompositionLocalOf { getColors(true) }
private val LocalTypography = staticCompositionLocalOf { CustomLeafTypography as LeafTypography }
private val LocalIsLightTheme = staticCompositionLocalOf { true }

@Composable
fun LeafTheme(
    theme: Theme = Theme.LIGHT,
    content: @Composable () -> Unit
) {
    val isLightTheme = when (theme) {
        Theme.DARK -> false
        Theme.LIGHT -> true
        Theme.SYSTEM -> !isSystemInDarkTheme()
    }

    val leafColors = getColors(isLightTheme)
    val leafTypography = CustomLeafTypography

    val configuration = LocalConfiguration.current
    val dimensions = when {
        configuration.screenWidthDp <= 360 -> smallDimensions
        configuration.screenWidthDp in 361..600 -> sw360Dimensions
        configuration.screenWidthDp in 601..940 -> sw600Dimensions
        configuration.screenWidthDp > 940 -> sw940Dimensions
        else -> smallDimensions
    }

    ProvideDimens(dimensions = dimensions) {
        ProvideAppColors(colors = leafColors) {
            ProvideTypography(typography = leafTypography) {
                ProvideIsLightTheme(isLightTheme = isLightTheme) {
                    MaterialTheme(
                        colorScheme = getColorsPalette(leafColors),
                        typography = getTypography(leafTypography),
                        content = content
                    )
                    UpdateSystemBarsColors(isLightTheme = isLightTheme, colors = leafColors)
                }
            }
        }
    }
}

@Composable
fun UpdateSystemBarsColors(isLightTheme: Boolean, colors: LeafColors) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = colors.systemBarColor, darkIcons = isLightTheme)
    }
}

object LeafTheme {
    val colors: LeafColors
        @Composable
        get() = LocalAppColors.current

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val typography: LeafTypography
        @Composable
        get() = LocalTypography.current

    val isLightTheme: Boolean
        @Composable
        get() = LocalIsLightTheme.current
}

val Dimens: Dimensions
    @Composable
    get() = LeafTheme.dimens

val Colors: LeafColors
    @Composable
    get() = LeafTheme.colors

val Typographs: LeafTypography
    @Composable
    get() = LeafTheme.typography

val IsLightTheme: Boolean
    @Composable
    get() = LeafTheme.isLightTheme