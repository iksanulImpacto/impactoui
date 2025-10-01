package com.impacto.impactoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun TextStatus(
    color: Color,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .width(98.dp)
            .height(28.dp)
            .background(color = color, shape = RoundedCornerShape(size = 8.dp))
            .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Preview
@Composable
fun Testing() {

    TextStatus(color = AppColors.Amber800) {
        Text("Belum Lunas", style = AppTextStyle.SmallNormal, color = AppColors.White)
    }
}
