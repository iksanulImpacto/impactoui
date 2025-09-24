package com.impacto.impactoui.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.R
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle

@Composable
fun AppBasicTextField(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    label: String? = null,
    errorText: String? = null,
    isFocused: Boolean = true,
    focusedBorderColor: Color = AppColors.Blue500,
    unfocusedBorderColor: Color = AppColors.Grey400,
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    backgroundColor: Color = Color.White,
    isSecure: Boolean = false,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val borderColor = when {
        isError -> errorBorderColor
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            ).padding(vertical = if(label == null || label == "" ) 8.dp else 0.dp)
    ) {
        Column {
            BasicTextField(
                value = "tes",
                onValueChange = { },
                singleLine = true,
                textStyle = AppTextStyle.MediumNormal,
                visualTransformation = if (isSecure && !isPasswordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .onFocusChanged { },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), // atur padding sendiri
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            if (label != null) {
                                Text(
                                    label,
                                    style = AppTextStyle.SmallNormal,
                                    modifier = Modifier.padding(bottom = 0.dp) // jarak 0
                                )
                            }
                            innerTextField()
                        }

                        if(isSecure) {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (isPasswordVisible) R.drawable.ic_visibility
                                        else R.drawable.ic_visibility_off
                                    ),
                                    contentDescription = "Toggle Password"
                                )
                            }
                        }
                    }
                }
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

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Testing() {
    var isError by remember { mutableStateOf(false) }

    AppBasicTextField(
        isError = isError,
        errorText = "",
        label = "string",
        isFocused = true
    )
}
