package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.domain.model.enums.AppLanguage
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import com.example.soundquest2.ui.intent.SettingsIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeStorage: ThemeStorage,
    private val languageStorage: LanguageStorage
) : ViewModel() {

    private val _theme = MutableStateFlow(AppTheme.LIGHT)
    val theme: StateFlow<AppTheme> = _theme.asStateFlow()

    private val _language = MutableStateFlow(AppLanguage.EN)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    init {
        viewModelScope.launch {
            _theme.value = themeStorage.getTheme()
            _language.value = languageStorage.getLanguage()
        }
    }

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.SetLanguage -> changeLanguage(intent.language)
            is SettingsIntent.SetTheme -> changeTheme(intent.theme)
        }
    }

    private fun changeTheme(newTheme: AppTheme) {
        viewModelScope.launch {
            themeStorage.setTheme(newTheme)
            _theme.value = newTheme
        }
    }

    private fun changeLanguage(newLanguage: AppLanguage) {
        viewModelScope.launch {
            languageStorage.setLanguage(newLanguage)
            _language.value = newLanguage
        }
    }
}
