package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.core.language.AppLanguage
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val themeStorage: ThemeStorage,
    private val languageStorage: LanguageStorage
) : ViewModel() {

    val theme: StateFlow<AppTheme> =
        themeStorage.themeFlow
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                AppTheme.LIGHT
            )

    val language: StateFlow<AppLanguage> =
        languageStorage.languageFlow
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                AppLanguage.RU
            )

    fun changeTheme(theme: AppTheme) {
        viewModelScope.launch {
            themeStorage.setTheme(theme)
        }
    }

    fun changeLanguage(language: AppLanguage) {
        viewModelScope.launch {
            languageStorage.setLanguage(language)
        }
    }
}
