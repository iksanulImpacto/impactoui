package com.impacto.impactoui.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun AppAdvanceField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    backgroundColor: Color = if (enabled) Color(0xFFE3F2FD) else Color(0xFFEEEEEE),
    borderColor: Color = if (isError) Color(0xFFF44336) else Color(0xFF2196F3),
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable RowScope.() -> Unit
) {
    Column(modifier = modifier) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, borderColor, shape)
                .background(backgroundColor, shape),
            shape = shape,
            color = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                content = content
            )
        }

        if (isError && !errorText.isNullOrEmpty()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = AppTextStyle.SmallNormal,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
