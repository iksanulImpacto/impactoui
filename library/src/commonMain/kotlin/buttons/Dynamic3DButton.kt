package com.impacto.impactoui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors

@Composable
fun Dynamic3DButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    listColor: List<Color> = listOf(
        if(enabled) AppColors.Blue500 else Color(0xFFF5F7F9), // biru terang atas
        if(enabled) AppColors.Blue700 else Color(0xFFF5F7F9) // biru agak gelap bawah
    ),
    elevationShadow: Dp = 4.dp,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val cornerRadius = 50.dp

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .shadow(
                elevation = elevationShadow, // bayangan untuk efek 3D
                shape = RoundedCornerShape(cornerRadius),
                clip = false
            )
            .background(
                brush = Brush.verticalGradient(
                    listColor
                ),
                shape = RoundedCornerShape(cornerRadius)
            ),
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // pakai gradient manual
            contentColor = AppColors.White,
            disabledContainerColor = Color(0xFFF5F7F9),
            disabledContentColor = Color(0xFFF5F7F9)
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 14.dp
        )
    ) {
        content()
    }
}
