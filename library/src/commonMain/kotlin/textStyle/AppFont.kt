package com.impacto.impactoui.textStyle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily

object AppFont {
    var Default by mutableStateOf<FontFamily?>(null)
}
