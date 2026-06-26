package com.impacto.impactoui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppFont
import com.impacto.impactoui.tokens.AppRadius

object ImpactoUI {
    var lightColorScheme by mutableStateOf<ColorScheme?>(null)
    var darkColorScheme by mutableStateOf<ColorScheme?>(null)

    internal val DefaultLightColorScheme = lightColorScheme(
        primary = AppColors.ImpactoPrimary,
        onPrimary = AppColors.White,
        primaryContainer = AppColors.Blue50,
        onPrimaryContainer = AppColors.Blue900,
        secondary = AppColors.Blue500,
        onSecondary = AppColors.White,
        secondaryContainer = AppColors.Blue100,
        onSecondaryContainer = AppColors.Blue900,
        error = AppColors.Red500,
        onError = AppColors.White,
        background = AppColors.White,
        onBackground = AppColors.Black,
        surface = AppColors.White,
        onSurface = AppColors.Black,
    )

    internal val DefaultDarkColorScheme = darkColorScheme(
        primary = AppColors.Blue200,
        onPrimary = AppColors.Grey900,
        primaryContainer = AppColors.Blue900,
        onPrimaryContainer = AppColors.Blue50,
        secondary = AppColors.Blue300,
        onSecondary = AppColors.Grey900,
        background = AppColors.Black,
        surface = AppColors.Grey900,
        error = AppColors.Red200,
    )

    /**
     * Get the current active color scheme.
     * Prioritizes custom schemes from config(), then fallbacks to Default schemes.
     */
    val colorScheme: ColorScheme
        @Composable
        get() = if (isSystemInDarkTheme()) {
            darkColorScheme ?: DefaultDarkColorScheme
        } else {
            lightColorScheme ?: DefaultLightColorScheme
        }

    /**
     * Configure ImpactoUI global settings.
     * Call this before using any ImpactoUI components, typically in your Application class or main entry point.
     * This will automatically update all components using these tokens.
     */
    fun config(
        fontFamily: FontFamily? = null,
        defaultRadius: Dp? = null,
        defaultSmallRadius: Dp? = null,
        lightColorScheme: ColorScheme? = null,
        darkColorScheme: ColorScheme? = null
    ) {
        fontFamily?.let { AppFont.Default = it }
        defaultRadius?.let { AppRadius.Default = it }
        defaultSmallRadius?.let { AppRadius.DefaultSmall = it }
        this.lightColorScheme = lightColorScheme
        this.darkColorScheme = darkColorScheme
    }
}
