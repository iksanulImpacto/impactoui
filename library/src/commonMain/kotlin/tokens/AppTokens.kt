package com.impacto.impactoui.tokens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object AppRadius {
    val None = 0.dp
    val Sm = 2.dp
    val Md = 4.dp
    val Lg = 8.dp
    val Xl = 12.dp
    val TwoXl = 16.dp
    val ThreeXl = 24.dp
    val FourXl = 32.dp
    val FiveXl = 48.dp
    val Full = 9999.dp

    var Default: Dp by mutableStateOf(Lg)
    var DefaultSmall: Dp by mutableStateOf(Lg)
}

object AppSpacing {
    val None = 0.dp
    val Sm = 2.dp
    val Md = 4.dp
    val Lg = 8.dp // Default
    val Xl = 12.dp
    val TwoXl = 16.dp
    val ThreeXl = 20.dp
    val FourXl = 24.dp
    val FiveXl = 32.dp
    val SixXl = 40.dp
}
