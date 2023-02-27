package com.github.sgeorgiev24.leaf.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

@Immutable
internal object LeafLightColors : LeafColors {
    override val primary: Color = GhostWhite
    override val onPrimary: Color = White
    override val primaryContainer: Color = GhostWhite
    override val onPrimaryContainer: Color = White
    override val inversePrimary: Color = Platinum
    override val secondary: Color = GreySuit
    override val onSecondary: Color = White
    override val secondaryContainer: Color = GreySuit
    override val onSecondaryContainer: Color = White
    override val tertiary: Color = GreySuit
    override val onTertiary: Color = GhostWhite
    override val tertiaryContainer: Color = GhostWhite
    override val onTertiaryContainer: Color = White
    override val background: Color = GhostWhite
    override val onBackground: Color = White
    override val surface: Color = GhostWhite
    override val onSurface: Color = White
    override val surfaceVariant: Color = GreySuit
    override val onSurfaceVariant: Color = GhostWhite
    override val surfaceTint: Color = White
    override val inverseSurface: Color = Platinum
    override val inverseOnSurface: Color = GhostWhite
    override val error: Color = Coral
    override val onError: Color = GhostWhite
    override val errorContainer: Color = GreySuit
    override val onErrorContainer: Color = GhostWhite
    override val outline: Color = GhostWhite
    override val outlineVariant: Color = White
    override val scrim: Color = GreySuit

    override val systemBarColor: Color = GhostWhite

    override val buttonContainerColor: Color = HadfieldBlue
    override val disabledButtonContainerColor: Color = HadfieldBlue.copy(alpha = .5f)
    override val buttonTextColor: Color = Platinum

    override val textFieldBackgroundColor: Color = White
    override val textFieldFocusedBackgroundColor: Color = HadfieldBlue
    override val textFieldUnfocusedBorderColor: Color = Color.Transparent

    override val cashFlowCardBackgroundColor: Color = White
    override val cashFlowCardValueColor: Color = Black
    override val cashFlowCardTitleColor: Color = GreySuit

    override val fabTextColor: Color = White
}