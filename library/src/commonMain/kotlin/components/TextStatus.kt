package com.impacto.impactoui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.textStyle.AppTextStyle

enum class TextStatusSize(
    val height: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val textStyle: TextStyle
) {
    Small(
        height = 16.dp,
        horizontalPadding = 8.dp,
        verticalPadding = 2.dp,
        textStyle = AppTextStyle.ExtraSmallNormal
    ),
    Medium(
        height = 28.dp,
        horizontalPadding = 12.dp,
        verticalPadding = 4.dp,
        textStyle = AppTextStyle.SmallNormal
    ),
    Large(
        height = 36.dp,
        horizontalPadding = 16.dp,
        verticalPadding = 6.dp,
        textStyle = AppTextStyle.MediumNormal
    )
}

@Composable
fun TextStatus(
    color: Color,
    size: TextStatusSize = TextStatusSize.Medium,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.(TextStyle) -> Unit
) {
    Row(
        modifier = modifier
            .height(size.height)
            .background(color = color, shape = RoundedCornerShape(size = 8.dp))
            .padding(
                start = size.horizontalPadding,
                end = size.horizontalPadding,
                top = size.verticalPadding,
                bottom = size.verticalPadding
            )
            .widthIn(min = 80.dp), // fleksibel width, minimal 80dp
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content(size.textStyle)
    }
}

//@Preview
//@Composable
//fun Testing() {
//    TextStatus(color = AppColors.Amber800, size = TextStatusSize.Medium) { style ->
//        Text("Belum Lunas", style = style, color = AppColors.White)
//    }
//}
