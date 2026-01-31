package com.example.soundquest2.ui.theme.common

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.soundquest2.ui.theme.style.BgStyle
import com.example.soundquest2.ui.theme.style.ButtonStyle
import com.example.soundquest2.ui.theme.style.MainCardStyle
import com.example.soundquest2.ui.theme.style.ResultCardStyle
import com.example.soundquest2.ui.theme.style.ThemeTextStyle

val LocalCustomThemeProperties = staticCompositionLocalOf<CustomThemeProperties> {
    error("No CustomThemeProperties provided")
}

data class CustomThemeProperties(
    val background: BgStyle,
    val card: MainCardStyle,
    val buttons: List<ButtonStyle>,
    val textStyle: ThemeTextStyle,
    val resultCard: ResultCardStyle
)
