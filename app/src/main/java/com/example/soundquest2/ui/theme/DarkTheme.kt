package com.example.soundquest2.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.soundquest2.ui.theme.common.CustomThemeProperties
import com.example.soundquest2.ui.theme.style.*

internal val DarkThemeProperties = CustomThemeProperties(
    background = BgStyle(
        listOf(
            Color.Black, Color.DarkGray, Color.Gray, Color.LightGray
        ),
        topColor = Color(0xFF323232)
    ),

    card = MainCardStyle(
        bottomColor = Color(0xFF626262),
        topColor = Color(0xFF484747)
    ),

    buttons = listOf(
        ButtonStyle(
            topBackgroundColor = Color(0xFFF7B3A4),
            bottomBackgroundColor = Color(0xFFEF8076),
            topBorderColor = Color(0xFFEB9D8F),
            bottomBorderColor = Color(0xFFD3685F)
        ),
        ButtonStyle(
            topBackgroundColor = Color(0xFF85D2B5),
            bottomBackgroundColor = Color(0xFF43AAB0),
            topBorderColor = Color(0xFF79C3A3),
            bottomBorderColor = Color(0xFF389399)
        ),
        ButtonStyle(
            topBackgroundColor = Color(0xFFA5A7E2),
            bottomBackgroundColor = Color(0xFF9B62C9),
            topBorderColor = Color(0xFF968ED5),
            bottomBorderColor = Color(0xFF7E4BAD)
        ),
        ButtonStyle(
            topBackgroundColor = Color(0xFFB3F587),
            bottomBackgroundColor = Color(0xFF5BB340),
            topBorderColor = Color(0xFF8DDC7F),
            bottomBorderColor = Color(0xFF449D60)
        ),
    ),

    textStyle = ThemeTextStyle(
        titleLarge = titleLarge(Color.White),
        titleSmall = titleSmall(Color.White),
        titleMedium = titleMedium(Color.White),
        labelSmall = labelSmall(Color.DarkGray),
        labelMedium = labelMedium(Color.DarkGray),
        labelLarge = labelLarge(Color.DarkGray),
        bodySmall = bodySmall(Color.White),
        bodyMedium = bodySmall(Color.White),
        bodyLarge = bodySmall(Color.White)
    ),

    resultCard = ResultCardStyle(
        cardColor = Color(0xFF626262),
        itemColor = Color.White
    )
)