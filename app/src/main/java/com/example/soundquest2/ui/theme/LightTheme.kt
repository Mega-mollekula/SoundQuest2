package com.example.soundquest2.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.soundquest2.ui.theme.common.CustomThemeProperties
import com.example.soundquest2.ui.theme.style.*

internal val LightThemeProperties = CustomThemeProperties(
    background = BgStyle(
        listOf(
            Color(0xFFFFF176), Color(0xFFFFB6C1), Color(0xFFBA68C8), Color(0xFF64B5F6),
            Color(0xFF81C784), Color(0xFFFFD54F), Color(0xFFFF8A65), Color(0xFF9575CD)
        ),
        topColor = Color(0xFF030e1f)
    ),

    card = MainCardStyle(
        bottomColor = Color(0xFFF5EBD6),
        topColor = Color.White
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
            topBackgroundColor = Color(0xFFFE9E9E),
            bottomBackgroundColor = Color(0xFFD55959),
            topBorderColor = Color(0xFFEA9494),
            bottomBorderColor = Color(0xFFB74F4F)
        ),
    ),

    textStyle = ThemeTextStyle(
        titleLarge = titleLarge(Color.DarkGray),
        titleSmall = titleSmall(Color.DarkGray),
        titleMedium = titleMedium(Color.DarkGray),
        labelSmall = labelSmall(Color.White),
        labelMedium = labelMedium(Color.White),
        labelLarge = labelLarge(Color.White),
        bodySmall = bodySmall(Color.DarkGray),
        bodyMedium = bodySmall(Color.DarkGray),
        bodyLarge = bodySmall(Color.DarkGray),
    ),

    resultCard = ResultCardStyle(
        cardColor = Color.LightGray,
        itemColor = Color.DarkGray
    )
)

