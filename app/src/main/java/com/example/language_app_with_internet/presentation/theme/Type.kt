package com.example.language_app_with_internet.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.languageapp.R

val fredokaFontFamily = FontFamily(
    Font(R.font.fredoka_regular, FontWeight.Normal),
    Font(R.font.fredoka_bold, FontWeight.Bold),
    Font(R.font.fredoka_semibold, FontWeight.SemiBold),
    Font(R.font.fredoka_medium, FontWeight.Medium),
)

val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.SemiBold

    ),
    headlineLarge = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.Bold
    )
)