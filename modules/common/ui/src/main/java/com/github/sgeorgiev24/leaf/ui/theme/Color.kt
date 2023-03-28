package com.github.sgeorgiev24.leaf.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

val ForestGreen = Color(0xFF228b22)
val Coral = Color(0xFFEB5C5F)
val Cinnabar = Color(0xFFE53935)
val GreySuit = Color(0xFF8E8E92)
val GhostWhite = Color(0xFFF2F2F6)
val HadfieldBlue = Color(0xFF0c79fe)
val Platinum = Color(0xFFE3E3E8)
val Pantone = Color(0xFF00897B)

fun getColors(isLightTheme: Boolean) = if (isLightTheme) LeafLightColors else LeafDarkColors

fun getColorsPalette(colors: LeafColors) =
    ColorScheme(
        primary = colors.primary,
        onPrimary = colors.onPrimary,
        primaryContainer = colors.primaryContainer,
        onPrimaryContainer = colors.onPrimaryContainer,
        inversePrimary = colors.inversePrimary,
        secondary = colors.secondary,
        onSecondary = colors.onSecondary,
        secondaryContainer = colors.secondaryContainer,
        onSecondaryContainer = colors.onSecondaryContainer,
        tertiary = colors.tertiary,
        onTertiary = colors.onTertiary,
        tertiaryContainer = colors.tertiaryContainer,
        onTertiaryContainer = colors.onTertiaryContainer,
        background = colors.background,
        onBackground = colors.onBackground,
        surface = colors.surface,
        onSurface = colors.onSurface,
        surfaceVariant = colors.surfaceVariant,
        onSurfaceVariant = colors.onSurfaceVariant,
        surfaceTint = colors.surfaceTint,
        inverseSurface = colors.inverseSurface,
        inverseOnSurface = colors.inverseOnSurface,
        error = colors.error,
        onError = colors.onError,
        errorContainer = colors.errorContainer,
        onErrorContainer = colors.onErrorContainer,
        outline = colors.outline,
        outlineVariant = colors.outlineVariant,
        scrim = colors.scrim
    )