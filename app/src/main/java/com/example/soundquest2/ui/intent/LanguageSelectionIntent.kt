package com.example.soundquest2.ui.intent

sealed interface LanguageSelectionIntent {
    data object RussianSelected : LanguageSelectionIntent
    data object EnglishSelected : LanguageSelectionIntent
    data object ExitClicked : LanguageSelectionIntent
}