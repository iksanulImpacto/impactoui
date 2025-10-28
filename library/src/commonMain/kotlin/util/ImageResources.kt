package com.impacto.impactoui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun imageResource(named: String): Painter
