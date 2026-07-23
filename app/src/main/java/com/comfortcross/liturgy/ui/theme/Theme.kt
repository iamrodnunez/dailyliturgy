package com.comfortcross.liturgy.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = Olive,
    onPrimary = Color.White,
    primaryContainer = ParchmentSurfaceAlt,
    onPrimaryContainer = OliveDeep,
    secondary = Wood,
    onSecondary = Color.White,
    tertiary = Gold,
    onTertiary = Color.White,
    background = Parchment,
    onBackground = Bark,
    surface = ParchmentSurface,
    onSurface = Bark,
    surfaceVariant = ParchmentSurfaceAlt,
    onSurfaceVariant = BarkSoft,
    outline = OutlineLight,
    outlineVariant = OutlineLight,
)

private val DarkColors = darkColorScheme(
    primary = OliveSoft,
    onPrimary = BarkNight,
    primaryContainer = BarkSurfaceAlt,
    onPrimaryContainer = OliveSoft,
    secondary = WoodSoft,
    onSecondary = BarkNight,
    tertiary = GoldSoft,
    onTertiary = BarkNight,
    background = BarkNight,
    onBackground = Cream,
    surface = BarkSurface,
    onSurface = Cream,
    surfaceVariant = BarkSurfaceAlt,
    onSurfaceVariant = CreamDim,
    outline = OutlineDark,
    outlineVariant = OutlineDark,
)

@Composable
fun ComfortCrossTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                colors.background.luminance() > 0.5f
        }
    }
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content,
    )
}
