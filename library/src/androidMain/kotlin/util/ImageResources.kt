package com.impacto.impactoui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(named: String): Painter {
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(named, "drawable", context.packageName)
    return painterResource(id = resourceId)
}
