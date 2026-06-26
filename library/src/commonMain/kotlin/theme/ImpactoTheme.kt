package com.impacto.impactoui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.impacto.impactoui.ImpactoUI
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle
import com.impacto.impactoui.tokens.AppRadius

@Composable
fun ImpactoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = ImpactoUI.colorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
