package com.impacto.impactoui.forms

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PlatformImeOptions

/**
 * Menyediakan [PlatformImeOptions] default untuk setiap platform.
 */
@Composable
expect fun rememberDefaultPlatformImeOptions(): PlatformImeOptions?
