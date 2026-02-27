package com.megamollekula.soundquest2.ui.intent

import com.megamollekula.soundquest2.domain.model.enums.AppLanguage
import com.megamollekula.soundquest2.domain.model.enums.AppTheme

sealed interface SettingsIntent {
    data class SetTheme(val theme: AppTheme) : SettingsIntent
    data class SetLanguage(val language: AppLanguage) : SettingsIntent
}