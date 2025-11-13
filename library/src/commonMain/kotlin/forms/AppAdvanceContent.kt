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
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle
import com.impacto.impactoui.textStyle.TextModifier

@Composable
fun AppAdvanceContent(
    modifier: Modifier = Modifier,
    title: String,
    placeholder: String,
    value: String? = null,
    titleStyle: TextModifier = TextModifier.apply {
        textStyle = AppTextStyle.SmallNormal
        textColor = AppColors.Blue800
    },
    valueStyle: TextModifier = TextModifier.apply {
        textStyle = AppTextStyle.RegularNormal
        textColor = AppColors.Blue600
    },
    placeholderStyle: TextModifier = TextModifier.apply {
        textStyle = AppTextStyle.RegularNormal
        textColor = AppColors.BlueGray400
    },
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
                    style = titleStyle.textStyle,
                    color = titleStyle.textColor
                )

                if (value == null) {
                    Text(
                        placeholder,
                        style = valueStyle.textStyle,
                        color = valueStyle.textColor
                    )
                }

                if (value != null) {
                    Text(
                        value,
                        style = placeholderStyle.textStyle,
                        color = placeholderStyle.textColor
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