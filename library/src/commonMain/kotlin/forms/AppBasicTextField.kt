package com.impacto.impactoui.forms

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.library.generated.resources.Res
import com.impacto.impactoui.library.generated.resources.ic_visibility
import com.impacto.impactoui.library.generated.resources.ic_visibility_off
import com.impacto.impactoui.textStyle.AppTextStyle
import org.jetbrains.compose.resources.painterResource

/* -------------------------------------------------------------
 * OVERLOAD 1: MODE STATE (TextFieldState)
 * ------------------------------------------------------------- */

@Composable
fun AppBasicTextField(
    modifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
    state: TextFieldState,
    placeholder: String? = null,
    maxLength: Int? = null,
    isError: Boolean = false,
    label: String? = null,
    errorText: String? = null,
    labelStyle: TextStyle = AppTextStyle.SmallNormal,
    labelStyleFocus: TextStyle = AppTextStyle.SmallNormal,
    placeholderStyle: TextStyle = AppTextStyle.MediumNormal.copy(color = AppColors.Grey400),
    valueStyle: TextStyle = AppTextStyle.MediumNormal.copy(color = AppColors.Grey400),
    focusedBorderColor: Color = AppColors.Blue500,
    unfocusedBorderColor: Color = AppColors.Grey400,
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    backgroundColor: Color = Color.White,
    isSecure: Boolean = false,
    enabled: Boolean = true,
    isMandatory: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    minHeight: Dp = 50.dp,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    val text = state.text.toString()
    val lineLimits =
        if (singleLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.MultiLine(maxHeightInLines = maxLines)

    AppBasicTextFieldCore(
        modifier = modifier,
        errorModifier = errorModifier,
        text = text,
        placeholder = placeholder,
        isError = isError,
        label = label,
        labelStyle = labelStyle,
        labelStyleFocus = labelStyleFocus,
        placeholderStyle = placeholderStyle,
        errorText = errorText,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        backgroundColor = backgroundColor,
        isSecure = isSecure,
        enabled = enabled,
        mandatory = isMandatory,
        prefix = prefix,
        suffix = suffix,
        trailingIcon = trailingIcon,
        minHeight = minHeight,
        shape = shape,
        paddingValues = paddingValues
    ) { fieldModifier, isPasswordVisible, decoration ->
        if (isSecure && !isPasswordVisible) {
            BasicSecureTextField(
                state = state,
                textStyle = valueStyle,
                inputTransformation = if (maxLength != null) InputTransformation.maxLength(maxLength) else null,
                enabled = enabled,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                modifier = fieldModifier,
                decorator = {
                    decoration(it)
                }
            )
        } else {
            BasicTextField(
                state = state,
                textStyle = valueStyle,
                enabled = enabled,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                modifier = fieldModifier,
                lineLimits = lineLimits,
                decorator = {
                    decoration(it)
                }
            )
        }
    }
}

/* -------------------------------------------------------------
 * OVERLOAD 2: MODE VALUE + onValueChange
 * ------------------------------------------------------------- */

@Composable
fun AppBasicTextField(
    modifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    labelStyle: TextStyle = AppTextStyle.SmallNormal,
    labelStyleFocus: TextStyle = AppTextStyle.SmallNormal,
    placeholderStyle: TextStyle = AppTextStyle.MediumNormal.copy(color = AppColors.Grey400),
    valueStyle: TextStyle = AppTextStyle.MediumNormal.copy(color = AppColors.Grey400),
    isError: Boolean = false,
    isMandatory: Boolean = false,
    label: String? = null,
    errorText: String? = null,
    focusedBorderColor: Color = AppColors.Blue500,
    unfocusedBorderColor: Color = AppColors.Grey400,
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    backgroundColor: Color = Color.White,
    isSecure: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    AppBasicTextFieldCore(
        modifier = modifier,
        errorModifier = errorModifier,
        text = value,
        placeholder = placeholder,
        isError = isError,
        label = label,
        errorText = errorText,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        backgroundColor = backgroundColor,
        labelStyle = labelStyle,
        labelStyleFocus = labelStyleFocus,
        placeholderStyle = placeholderStyle,
        isSecure = isSecure,
        mandatory = isMandatory,
        enabled = enabled,
        prefix = prefix,
        suffix = suffix,
        trailingIcon = trailingIcon,
        shape = shape,
        paddingValues = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
    ) { fieldModifier, isPasswordVisible, decoration ->
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = valueStyle,
            visualTransformation = if (isSecure && !isPasswordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = fieldModifier,
            decorationBox = decoration
        )
    }
}

/* -------------------------------------------------------------
 * CORE LAYOUT (dipakai kedua overload)
 * ------------------------------------------------------------- */

@Composable
private fun AppBasicTextFieldCore(
    modifier: Modifier,
    errorModifier: Modifier,
    text: String,
    placeholder: String?,
    isError: Boolean,
    label: String?,
    errorText: String?,
    focusedBorderColor: Color,
    unfocusedBorderColor: Color,
    errorBorderColor: Color,
    backgroundColor: Color,
    isSecure: Boolean,
    enabled: Boolean,
    mandatory: Boolean,
    labelStyle: TextStyle,
    labelStyleFocus: TextStyle,
    placeholderStyle: TextStyle,
    prefix: @Composable (() -> Unit)?,
    suffix: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    shape: RoundedCornerShape,
    minHeight: Dp = 50.dp,
    paddingValues: PaddingValues,
    fieldContent: @Composable (
        fieldModifier: Modifier,
        isPasswordVisible: Boolean,
        decoration: @Composable (@Composable () -> Unit) -> Unit
    ) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val shouldFloat = isFocused || text.isNotEmpty()

    val offsetY by animateDpAsState(
        if (shouldFloat) 0.dp else 10.dp,
        label = ""
    )

    val scale by animateFloatAsState(
        1f,
        label = ""
    )

    val borderColor = when {
        isError -> errorBorderColor
        isFocused -> focusedBorderColor
        else -> unfocusedBorderColor
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .heightIn(min = minHeight)
                .background(backgroundColor, shape)
                .border(
                    width = 1.5.dp,
                    color = borderColor,
                    shape = shape
                )
                .padding(vertical = if (label.isNullOrEmpty()) 8.dp else 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {

                val fieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = paddingValues)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }

                // dekorasi bersama
                val decoration: @Composable (@Composable () -> Unit) -> Unit = { innerTextField ->
                    Column {
                        if (!label.isNullOrEmpty()) {
                            Row(
                                modifier = Modifier.graphicsLayer {
                                    translationY = offsetY.toPx()
                                    scaleX = scale
                                    scaleY = scale
                                }.padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = label,
                                    style = if (shouldFloat) labelStyleFocus else labelStyle,
                                )

                                if (mandatory) {
                                    Spacer(Modifier.width(2.dp))
                                    Text(
                                        text = "*",
                                        color = Color.Red
                                    )
                                }
                            }
                        }

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            if (text.isEmpty() &&
                                !placeholder.isNullOrEmpty() &&
                                !isFocused
                            ) {
                                Text(
                                    text = placeholder,
                                    style = placeholderStyle
                                )
                            }
                            Row {
                                // prefix
                                if (
                                    (prefix != null && isFocused) ||
                                    (prefix != null && text.isNotEmpty() && !isFocused)
                                ) {
                                    prefix()
                                }
                                innerTextField()
                                Spacer(Modifier.weight(1f))
                                if (
                                    (suffix != null && isFocused) ||
                                    (suffix != null && text.isNotEmpty() && !isFocused)
                                ) {
                                    suffix()
                                }
                            }
                        }
                    }
                }
                fieldContent(fieldModifier, isPasswordVisible, decoration)
            }

            trailingIcon?.let {
                IconButton(onClick = { }) { it() }
            }

            if (isSecure) {
                IconButton(
                    enabled = enabled,
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible) Res.drawable.ic_visibility
                            else Res.drawable.ic_visibility_off
                        ),
                        tint = Color.Unspecified,
                        contentDescription = "Toggle Password"
                    )
                }
            }
        }

        if (isError && !errorText.isNullOrEmpty()) {
            Text(
                text = errorText,
                color = errorBorderColor,
                style = AppTextStyle.SmallNormal,
                modifier = errorModifier.padding(top = 8.dp)
            )
        }
    }
}