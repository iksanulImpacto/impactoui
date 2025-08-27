package com.impacto.impactoui.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.xr.compose.testing.isFocused

import androidx.compose.ui.res.colorResource
import com.impacto.impactoui.R // pastikan ini sesuai dengan package resource kamu

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
    isFocused: Boolean = true,
    focusedBorderColor: Color = Color(0xFF2196F3),
    unfocusedBorderColor: Color = Color(0xFFBDBDBD),
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    backgroundColor: Color = Color.White,
    content: @Composable (modifier: Modifier, isError: Boolean) -> Unit
) {
    val borderColor = when {
        isError -> errorBorderColor
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }

    Column(modifier = modifier) {
        content(
            Modifier
                .fillMaxWidth()
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .border(
                    width = 1.5.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                ),
            isError
        )

        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = errorBorderColor,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
