package com.impacto.impactoui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun AppAdvanceContent(
    modifier: Modifier = Modifier,
    title: String,
    placeholder: String,
    value: String? = null,
    titleStyle: TextStyle = AppTextStyle.SmallNormal.copy(color = AppColors.Blue800),
    valueStyle: TextStyle = AppTextStyle.RegularNormal.copy(color = AppColors.Blue600),
    placeholderStyle: TextStyle = AppTextStyle.RegularNormal.copy(color = AppColors.BlueGray400),
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    content: @Composable (() -> Unit)? = null
) {
    Column {
        Row (
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    modifier = Modifier.size(width = 20.dp, height = 20.dp),
                    painter = leadingIcon,
                    contentDescription = null,
                )
            }

            Column (
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    title,
                    style = titleStyle,
                )

                if (value == null) {
                    Text(
                        placeholder,
                        style = placeholderStyle
                    )
                }

                if (value != null) {
                    Text(
                        value,
                        style = valueStyle,

                    )
                }
            }

            Spacer(Modifier.weight(1f))

            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier.size(width = 20.dp, height = 20.dp),
                    painter = trailingIcon,
                    contentDescription = null,
                    tint = AppColors.Blue800
                )
            }

        }

        content?.invoke()
    }

}