package com.example.soundquest2.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.soundquest2.ui.theme.common.CustomThemeProperties
import com.example.soundquest2.ui.theme.common.LocalCustomThemeProperties

@Composable
fun SoundQuestThemeProvider(
    theme: CustomThemeProperties,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomThemeProperties provides theme,
        content = content
    )
}
