package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.core.language.AppLanguage
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import com.example.soundquest2.ui.intent.SettingsIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val themeStorage: ThemeStorage,
    private val languageStorage: LanguageStorage,
    initialTheme: AppTheme,
    initialLanguage: AppLanguage
) : ViewModel() {

    private val _theme = MutableStateFlow(initialTheme)
    val theme: StateFlow<AppTheme> = _theme.asStateFlow()

    private val _language = MutableStateFlow(initialLanguage)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.SetLanguage -> changeLanguage(intent.language)

            is SettingsIntent.SetTheme -> changeTheme(intent.theme)
        }
    }

    fun changeTheme(newTheme: AppTheme) {
        viewModelScope.launch {
            themeStorage.setTheme(newTheme)
            _theme.update {
                newTheme
            }
        }
    }

    fun changeLanguage(newLanguage: AppLanguage) {
        viewModelScope.launch {
            languageStorage.setLanguage(newLanguage)
            _language.update {
                newLanguage
            }
        }
    }
}
