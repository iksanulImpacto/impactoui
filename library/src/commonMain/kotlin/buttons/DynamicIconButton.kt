package com.impacto.impactoui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize

@Composable
fun DynamicIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String? = null,
    imageSize: DpSize? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .wrapContentSize()
    ) {
        Image(
            painter = icon,
            contentDescription = contentDescription,
            modifier = if (imageSize != null) {
                Modifier.size(imageSize)
            } else {
                Modifier.wrapContentSize()
            }
        )
    }
}
