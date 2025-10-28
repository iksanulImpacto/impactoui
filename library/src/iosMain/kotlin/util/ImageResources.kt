package com.impacto.impactoui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(named: String): Painter {
    // For this to work on iOS, you need to add your images
    // (e.g., 'ic_check_circle.pdf' or 'ic_check_circle.svg')
    // to the `iosApp/Assets.xcassets` in your Xcode project.
    // The 'named' string here should match the name of the image set in Xcode.
    //
    // NOTE: Android's XML vector drawables are not directly supported.
    // You will need to convert them to a format like PDF or SVG for iOS.
    return painterResource(res = named)
}
