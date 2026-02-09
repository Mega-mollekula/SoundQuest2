package com.example.soundquest2.ui.intent

sealed interface SettingsIntent {
    data object ThemeClicked : SettingsIntent
    data object LanguageClicked : SettingsIntent
    data object ExitClicked : SettingsIntent
}