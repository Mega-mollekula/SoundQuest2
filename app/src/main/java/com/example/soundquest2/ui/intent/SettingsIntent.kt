package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.enums.AppLanguage
import com.example.soundquest2.core.theme.AppTheme

sealed interface SettingsIntent {
    data class SetTheme(val theme: AppTheme) : SettingsIntent
    data class SetLanguage(val language: AppLanguage) : SettingsIntent
}