package com.impacto.impactoui.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.impacto.impactoui.ImpactoUI
import com.impacto.impactoui.colors.AppColors
import com.impacto.impactoui.textStyle.AppTextStyle
import com.impacto.impactoui.tokens.AppRadius
import com.impacto.impactoui.tokens.AppSpacing
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class ImpactoButtonType {
    Filled,
    Elevated,
    Tonal,
    Outlined,
    Text
}

enum class ImpactoButtonSize {
    L, M, S, XS
}

enum class ImpactoIconPosition {
    Leading,
    Trailing
}

/**
 * Unified Button component for ImpactoUI.
 *
 * @param onClick Callback when button is clicked.
 * @param modifier Modifier for external layout (size, padding, etc).
 * @param type The type of button based on Material Design 3 (Filled, Elevated, etc).
 * @param buttonSize The size of the button (L, M, S, XS).
 * @param text Optional text to display inside the button.
 * @param icon Optional icon to display.
 * @param iconPosition Position of the icon relative to the text.
 * @param enabled Whether the button is interactive.
 * @param isLoading When true, shows a progress indicator and disables interaction.
 * @param shape The shape of the button.
 * @param colors Custom colors for the button. Defaults to Impacto branding.
 * @param backgroundBrush Optional gradient brush for the background.
 * @param borderBrush Optional gradient brush for the border.
 * @param borderWidth Width of the border when [borderBrush] is used.
 * @param showInnerShadow When true, adds a subtle inner shadow (best for Filled type).
 * @param contentPadding Internal padding of the button.
 * @param content Optional custom content lambda. If provided, [text] and [icon] will be ignored.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImpactoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: ImpactoButtonType = ImpactoButtonType.Filled,
    buttonSize: ImpactoButtonSize = ImpactoButtonSize.M,
    text: String? = null,
    icon: DrawableResource? = null,
    iconPosition: ImpactoIconPosition = ImpactoIconPosition.Leading,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    shape: Shape? = null,
    colors: ButtonColors? = null,
    backgroundBrush: Brush? = null,
    borderBrush: Brush? = null,
    borderWidth: Dp = 1.dp,
    showInnerShadow: Boolean = false,
    contentPadding: PaddingValues? = null,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animasi Transisi Bayangan
    val highlightAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0f else 0.5f,
        label = "highlightAlpha"
    )
    val shadowAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.25f else 0.45f,
        label = "shadowAlpha"
    )
    val shadowPositionFactor by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f, // 0: Bawah (Normal), 1: Atas (Pressed)
        label = "shadowPosition"
    )

    // Determine the button implementation based on type
    val buttonOnClick = { if (!isLoading) onClick() }
    val buttonEnabled = enabled && !isLoading

    val finalShape = shape ?: when (buttonSize) {
        ImpactoButtonSize.L, ImpactoButtonSize.M -> RoundedCornerShape(AppRadius.Default)
        ImpactoButtonSize.S, ImpactoButtonSize.XS -> RoundedCornerShape(AppRadius.DefaultSmall)
    }

    // Size-based values
    val height = when (buttonSize) {
        ImpactoButtonSize.L -> 56.dp
        ImpactoButtonSize.M -> 48.dp
        ImpactoButtonSize.S, ImpactoButtonSize.XS -> 36.dp
    }

    val textStyle = when (buttonSize) {
        ImpactoButtonSize.L -> AppTextStyle.MediumMedium.copy(textAlign = TextAlign.Center)
        ImpactoButtonSize.M -> AppTextStyle.MediumMedium.copy(textAlign = TextAlign.Center)
        ImpactoButtonSize.S -> AppTextStyle.SmallMedium.copy(textAlign = TextAlign.Center)
        ImpactoButtonSize.XS -> AppTextStyle.SmallMedium.copy(textAlign = TextAlign.Center)
    }

    val iconSize = when (buttonSize) {
        ImpactoButtonSize.L -> 24.dp
        else -> 20.dp
    }

    val defaultPadding = when (buttonSize) {
        ImpactoButtonSize.L -> PaddingValues(
            horizontal = AppSpacing.FiveXl,
            vertical = AppSpacing.TwoXl
        )
        ImpactoButtonSize.M -> PaddingValues(
            horizontal = AppSpacing.FourXl,
            vertical = AppSpacing.Xl
        )
        ImpactoButtonSize.S, ImpactoButtonSize.XS -> PaddingValues(
            horizontal = AppSpacing.TwoXl,
            vertical = AppSpacing.Lg
        )
    }

    val finalContentPadding = contentPadding ?: defaultPadding
    val colorScheme = ImpactoUI.colorScheme

    // Handle Background (Solid or Gradient) and Inner Shadow
    val finalModifier = modifier
        .size(height = height, width = Dp.Unspecified) // Set height and width based on size
        .clip(finalShape) // PENTING: Clip dulu agar background tidak melebar ke area touch target
        .then(
            if ((backgroundBrush != null) || (showInnerShadow && type == ImpactoButtonType.Filled)) {
                Modifier.background(
                    brush = when {
                        !buttonEnabled -> SolidColor(AppColors.Grey200)
                        backgroundBrush != null -> backgroundBrush
                        else -> SolidColor(colorScheme.primary) // Default solid color
                    },
                    shape = finalShape
                )
            } else Modifier
        )
        .then(
            if (showInnerShadow && buttonEnabled) {
                Modifier.drawWithContent {
                    drawContent()
                    val outline = finalShape.createOutline(size, layoutDirection, this)
                    val path = Path().apply { addOutline(outline) }

                    clipPath(path) {
                        // 1. Highlight Terang di ATAS (Normal State)
                        if (highlightAlpha > 0f) {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0.0f to Color.White.copy(alpha = highlightAlpha),
                                    0.15f to Color.Transparent
                                )
                            )
                        }

                        // 2. Shadow Gelap
                        // Shadow di BAWAH (Normal State)
                        if (1f - shadowPositionFactor > 0f) {
                            val currentAlpha = shadowAlpha * (0.6f - shadowPositionFactor)
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0.7f to Color.Transparent,
                                    1.0f to Color.Black.copy(alpha = currentAlpha)
                                )
                            )
                        }
                        
                        // Shadow di ATAS (Pressed State)
                        if (shadowPositionFactor > 0f) {
                            val currentAlpha = shadowAlpha * shadowPositionFactor
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0.0f to Color.Black.copy(alpha = currentAlpha),
                                    0.4f to Color.Transparent
                                )
                            )
                        }
                    }
                }
            } else Modifier
        )

    // Handle Border (Gradient or Solid for Outlined)
    val borderStroke = when {
        borderBrush != null -> {
            BorderStroke(borderWidth, if (buttonEnabled) borderBrush else SolidColor(AppColors.Grey200))
        }
        type == ImpactoButtonType.Outlined -> {
            BorderStroke(borderWidth, if (buttonEnabled) SolidColor(colorScheme.primary) else SolidColor(AppColors.Grey200))
        }
        else -> null
    }
    
    // Default colors if not provided
    val defaultColors = colors ?: if (backgroundBrush != null || showInnerShadow) {
        ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Paksa transparan agar background dari modifier yang terlihat
            contentColor = if (type == ImpactoButtonType.Filled) colorScheme.onPrimary else colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppColors.Grey400
        )
    } else {
        when (type) {
            ImpactoButtonType.Filled -> ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary,
                disabledContainerColor = AppColors.Grey200,
                disabledContentColor = AppColors.Grey400
            )
            ImpactoButtonType.Outlined -> ButtonDefaults.outlinedButtonColors(
                contentColor = colorScheme.primary,
                disabledContentColor = AppColors.Grey400
            )
            ImpactoButtonType.Text -> ButtonDefaults.textButtonColors(
                contentColor = colorScheme.primary,
                disabledContentColor = AppColors.Grey400
            )
            ImpactoButtonType.Tonal -> ButtonDefaults.filledTonalButtonColors(
                containerColor = colorScheme.secondaryContainer,
                contentColor = colorScheme.primary,
                disabledContainerColor = AppColors.Grey200,
                disabledContentColor = AppColors.Grey400
            )
            ImpactoButtonType.Elevated -> ButtonDefaults.elevatedButtonColors(
                containerColor = colorScheme.surface,
                contentColor = colorScheme.primary,
                disabledContainerColor = AppColors.Grey200,
                disabledContentColor = AppColors.Grey400
            )
        }
    }

    val gap = when (buttonSize) {
        ImpactoButtonSize.XS, ImpactoButtonSize.S -> AppSpacing.Md
        else -> AppSpacing.Lg
    }

    val buttonContent: @Composable RowScope.() -> Unit = {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(iconSize).align(Alignment.CenterVertically),
                color = if (type == ImpactoButtonType.Filled) colorScheme.onPrimary else colorScheme.primary,
                strokeWidth = 2.dp
            )
        } else if (content != null) {
            content()
        } else {
            if (icon != null && iconPosition == ImpactoIconPosition.Leading) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).align(Alignment.CenterVertically)
                )
                if (!text.isNullOrEmpty()) Spacer(Modifier.width(gap))
            }

            text?.let {
                Text(
                    text = it,
                    style = textStyle,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            if (icon != null && iconPosition == ImpactoIconPosition.Trailing) {
                if (!text.isNullOrEmpty()) Spacer(Modifier.width(gap))
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).align(Alignment.CenterVertically)
                )
            }
        }
    }

    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides Dp.Unspecified,
        LocalRippleConfiguration provides if (showInnerShadow) null else LocalRippleConfiguration.current
    ) {
        when (type) {
            ImpactoButtonType.Filled -> Button(
                onClick = buttonOnClick,
                modifier = finalModifier,
                enabled = buttonEnabled,
                shape = finalShape,
                colors = defaultColors,
                border = borderStroke,
                contentPadding = finalContentPadding,
                interactionSource = interactionSource,
                content = buttonContent
            )
            ImpactoButtonType.Elevated -> ElevatedButton(
                onClick = buttonOnClick,
                modifier = finalModifier,
                enabled = buttonEnabled,
                shape = finalShape,
                colors = defaultColors,
                border = borderStroke,
                contentPadding = finalContentPadding,
                interactionSource = interactionSource,
                content = buttonContent
            )
            ImpactoButtonType.Tonal -> FilledTonalButton(
                onClick = buttonOnClick,
                modifier = finalModifier,
                enabled = buttonEnabled,
                shape = finalShape,
                colors = defaultColors,
                border = borderStroke,
                contentPadding = finalContentPadding,
                interactionSource = interactionSource,
                content = buttonContent
            )
            ImpactoButtonType.Outlined -> OutlinedButton(
                onClick = buttonOnClick,
                modifier = finalModifier,
                enabled = buttonEnabled,
                shape = finalShape,
                colors = defaultColors,
                border = borderStroke,
                contentPadding = finalContentPadding,
                interactionSource = interactionSource,
                content = buttonContent
            )
            ImpactoButtonType.Text -> TextButton(
                onClick = buttonOnClick,
                modifier = finalModifier,
                enabled = buttonEnabled,
                shape = finalShape,
                colors = defaultColors,
                border = borderStroke,
                contentPadding = finalContentPadding,
                interactionSource = interactionSource,
                content = buttonContent
            )
        }
    }
}
