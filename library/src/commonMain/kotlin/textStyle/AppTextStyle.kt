package com.impacto.impactoui.textStyle

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

object AppTextStyle {

    private val defaultFont get() = AppFont.Default ?: FontFamily.Default

    private fun createTextStyle(
        fontWeight: FontWeight,
        fontSize: TextUnit,
        lineHeight: TextUnit
    ) = TextStyle(
        fontFamily = defaultFont,
        fontWeight = fontWeight,
        fontSize = fontSize,
        lineHeight = lineHeight
    )

    val ExtraSmallNormal get() = createTextStyle(FontWeight.Normal, 10.sp, 16.sp)
    val ExtraSmallMedium get() = createTextStyle(FontWeight.Medium, 10.sp, 16.sp)
    val ExtraSmallSemiBold get() = createTextStyle(FontWeight.SemiBold, 10.sp, 16.sp)
    val ExtraSmallBold get() = createTextStyle(FontWeight.Bold, 10.sp, 16.sp)

    val SmallNormal get() = createTextStyle(FontWeight.Normal, 12.sp, 20.sp)
    val SmallMedium get() = createTextStyle(FontWeight.Medium, 12.sp, 20.sp)
    val SmallSemiBold get() = createTextStyle(FontWeight.SemiBold, 12.sp, 20.sp)
    val SmallBold get() = createTextStyle(FontWeight.Bold, 12.sp, 20.sp)

    val RegularNormal get() = createTextStyle(FontWeight.Normal, 14.sp, 22.sp)
    val RegularMedium get() = createTextStyle(FontWeight.Medium, 14.sp, 22.sp)
    val RegularSemiBold get() = createTextStyle(FontWeight.SemiBold, 14.sp, 22.sp)
    val RegularBold get() = createTextStyle(FontWeight.Bold, 14.sp, 22.sp)

    val MediumNormal get() = createTextStyle(FontWeight.Normal, 16.sp, 24.sp)
    val MediumMedium get() = createTextStyle(FontWeight.Medium, 16.sp, 24.sp)
    val MediumSemiBold get() = createTextStyle(FontWeight.SemiBold, 16.sp, 24.sp)
    val MediumBold get() = createTextStyle(FontWeight.Bold, 16.sp, 24.sp)

    val LargeNormal get() = createTextStyle(FontWeight.Normal, 18.sp, 30.sp)
    val LargeMedium get() = createTextStyle(FontWeight.Medium, 18.sp, 30.sp)
    val LargeSemiBold get() = createTextStyle(FontWeight.SemiBold, 18.sp, 30.sp)
    val LargeBold get() = createTextStyle(FontWeight.Bold, 18.sp, 30.sp)

    val ExtraLargeNormal get() = createTextStyle(FontWeight.Normal, 24.sp, 36.sp)
    val ExtraLargeMedium get() = createTextStyle(FontWeight.Medium, 24.sp, 36.sp)
    val ExtraLargeSemiBold get() = createTextStyle(FontWeight.SemiBold, 24.sp, 36.sp)
    val ExtraLargeBold get() = createTextStyle(FontWeight.Bold, 24.sp, 36.sp)
}
