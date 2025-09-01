package com.impacto.impactoui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DynamicButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    borderColor: Color? = null,
    borderWidth: Dp = 1.5.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    paddingHorizontal: Dp = 16.dp,
    paddingVertical: Dp = 12.dp,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val buttonModifier = if (borderColor != null) {
        modifier
            .border(borderWidth, borderColor, shape)
            .background(backgroundColor, shape)
    } else {
        modifier.background(backgroundColor, shape)
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = contentColor.copy(alpha = 0.3f)
        ),
        contentPadding = PaddingValues(
            horizontal = paddingHorizontal,
            vertical = paddingVertical
        )
    ) {
        content()
    }
}

