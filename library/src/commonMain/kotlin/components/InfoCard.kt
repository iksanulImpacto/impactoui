package com.impacto.impactoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    containerRadius: Dp = 12.dp,
    elevationCard: Dp = 0.dp,
    containerColor: Color = AppColors.White,
    backgroundBrush: Brush? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(containerRadius),
        colors = CardDefaults.cardColors(
            containerColor = if (backgroundBrush != null) Color.Transparent else containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevationCard)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (backgroundBrush != null) {
                        Modifier.background(backgroundBrush)
                    } else {
                        Modifier
                    }
                )
                .padding(contentPadding)
        ) {
            content()
        }
    }
}
