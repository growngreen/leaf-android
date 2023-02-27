package com.github.sgeorgiev24.leaf.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
internal object LeafDarkColors : LeafColors {
    override val primary: Color = GhostWhite
    override val onPrimary: Color = Color.White
    override val primaryContainer: Color = GhostWhite
    override val onPrimaryContainer: Color = Color.White
    override val inversePrimary: Color = Platinum
    override val secondary: Color = GreySuit
    override val onSecondary: Color = Color.White
    override val secondaryContainer: Color = GreySuit
    override val onSecondaryContainer: Color = Color.White
    override val tertiary: Color = GreySuit
    override val onTertiary: Color = GhostWhite
    override val tertiaryContainer: Color = GhostWhite
    override val onTertiaryContainer: Color = Color.White
    override val background: Color = Color.White
    override val onBackground: Color = Color.White
    override val surface: Color = GhostWhite
    override val onSurface: Color = Color.White
    override val surfaceVariant: Color = GreySuit
    override val onSurfaceVariant: Color = GhostWhite
    override val surfaceTint: Color = Color.White
    override val inverseSurface: Color = Platinum
    override val inverseOnSurface: Color = GhostWhite
    override val error: Color = Coral
    override val onError: Color = GhostWhite
    override val errorContainer: Color = GreySuit
    override val onErrorContainer: Color = GhostWhite
    override val outline: Color = GhostWhite
    override val outlineVariant: Color = Color.White
    override val scrim: Color = GreySuit

    override val systemBarColor: Color = Color.White

    override val buttonContainerColor: Color = HadfieldBlue
    override val disabledButtonContainerColor: Color = HadfieldBlue.copy(alpha = .5f)
    override val buttonTextColor: Color = Platinum

    override val textFieldBackgroundColor: Color = Color.White
    override val textFieldFocusedBackgroundColor: Color = HadfieldBlue
    override val textFieldUnfocusedBorderColor: Color = Color.Transparent

    override val cashFlowCardBackgroundColor: Color = Color.White
    override val cashFlowCardValueColor: Color = Color.Black
    override val cashFlowCardTitleColor: Color = GreySuit

    override val fabTextColor: Color = Color.White
}