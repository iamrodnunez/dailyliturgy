package com.comfortcross.liturgy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Serif = FontFamily.Serif

// Serif headings for a quiet, liturgical feel; comfortable reading measure for prayer.
val AppTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.Medium,
        fontSize = 30.sp, lineHeight = 38.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.Medium,
        fontSize = 24.sp, lineHeight = 30.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.Medium,
        fontSize = 20.sp, lineHeight = 26.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp, lineHeight = 26.sp, letterSpacing = 0.2.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp, lineHeight = 22.sp, letterSpacing = 0.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium,
        fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 0.8.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium,
        fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 1.0.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.Normal,
        fontSize = 18.sp, lineHeight = 28.sp, letterSpacing = 0.1.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Serif, fontWeight = FontWeight.Normal,
        fontSize = 16.sp, lineHeight = 25.sp, letterSpacing = 0.1.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,
        fontSize = 13.sp, lineHeight = 18.sp,
    ),
)
