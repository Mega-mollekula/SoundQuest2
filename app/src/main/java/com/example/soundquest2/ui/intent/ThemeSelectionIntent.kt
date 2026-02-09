package com.example.soundquest2.ui.intent

sealed interface ThemeSelectionIntent {
    data object LightSelected : ThemeSelectionIntent
    data object DarkSelected : ThemeSelectionIntent
    data object ExitClicked : ThemeSelectionIntent
}