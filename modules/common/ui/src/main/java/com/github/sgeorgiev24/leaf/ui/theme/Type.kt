package com.github.sgeorgiev24.leaf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.sgeorgiev24.leaf.ui.R

private val PlusJakartaSans = FontFamily(
    Font(R.font.source_sans_pro_regular),
    Font(R.font.source_sans_pro_bold, FontWeight.Bold),
    Font(R.font.source_sans_pro_black, FontWeight.ExtraBold)
)

private val defaultFontFeatureSettings: String = "liga off"
private val defaultLetterSpacing = .1.sp

private val H1 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 26.sp,
    lineHeight = 38.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val H1_5 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 24.sp,
    lineHeight = 38.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val H2 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
    lineHeight = 30.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val H3 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 28.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val H4 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Body1 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 26.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Body1Bold = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 26.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Body1_5 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 24.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Body2 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Body2Bold = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Label = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Button1 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 28.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val Button2 = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 26.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)
private val Small = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 22.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

private val SmallBold = TextStyle(
    fontFamily = PlusJakartaSans,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    lineHeight = 22.sp,
    letterSpacing = defaultLetterSpacing,
    fontFeatureSettings = defaultFontFeatureSettings
)

interface LeafTypography {
    val displayLarge: TextStyle
    val displayMedium: TextStyle
    val displaySmall: TextStyle
    val headlineLarge: TextStyle
    val headlineMedium: TextStyle
    val headlineSmall: TextStyle
    val titleLarge: TextStyle
    val titleHuge: TextStyle
    val titleMedium: TextStyle
    val titleSmall: TextStyle
    val bodyLarge: TextStyle
    val bodyMedium: TextStyle
    val bodySmall: TextStyle
    val labelLarge: TextStyle
    val labelMedium: TextStyle
    val labelSmall: TextStyle

    val h1: TextStyle
    val h2: TextStyle
    val h3: TextStyle
    val h4: TextStyle
    val body1: TextStyle
    val body1Bold: TextStyle
    val body1_5: TextStyle
    val body2: TextStyle
    val body2Bold: TextStyle
    val label: TextStyle
    val button1: TextStyle
    val button2: TextStyle
    val small: TextStyle
    val smallBold: TextStyle
}

@Immutable
object CustomLeafTypography : LeafTypography {
    override val displayLarge: TextStyle = H1
    override val displayMedium: TextStyle = H2
    override val displaySmall: TextStyle = H3
    override val headlineLarge: TextStyle = H1
    override val headlineMedium: TextStyle = H2
    override val headlineSmall: TextStyle = H3
    override val titleLarge: TextStyle = H1
    override val titleHuge: TextStyle = H1_5
    override val titleMedium: TextStyle = H2
    override val titleSmall: TextStyle = H3
    override val bodyLarge: TextStyle = Body1Bold
    override val bodyMedium: TextStyle = Body1
    override val bodySmall: TextStyle = Body2
    override val labelLarge: TextStyle = H4
    override val labelMedium: TextStyle = Label
    override val labelSmall: TextStyle = Label

    override val h1: TextStyle = H1
    override val h2: TextStyle = H2
    override val h3: TextStyle = H3
    override val h4: TextStyle = H4
    override val body1: TextStyle = Body1
    override val body1Bold: TextStyle = Body1Bold
    override val body1_5: TextStyle = Body1_5
    override val body2: TextStyle = Body2
    override val body2Bold: TextStyle = Body2Bold
    override val label: TextStyle = Label
    override val button1: TextStyle = Button1
    override val button2: TextStyle = Button2
    override val small: TextStyle = Small
    override val smallBold: TextStyle = SmallBold
}

fun getTypography(typography: LeafTypography) =
    Typography(
        displayLarge = typography.displayLarge,
        displayMedium = typography.displayMedium,
        displaySmall = typography.displaySmall,
        headlineLarge = typography.headlineLarge,
        headlineMedium = typography.headlineMedium,
        headlineSmall = typography.headlineSmall,
        titleLarge = typography.titleLarge,
        titleMedium = typography.titleMedium,
        titleSmall = typography.titleSmall,
        bodyLarge = typography.bodyLarge,
        bodyMedium = typography.bodyMedium,
        bodySmall = typography.bodySmall,
        labelLarge = typography.labelLarge,
        labelMedium = typography.labelMedium,
        labelSmall = typography.labelSmall
    )