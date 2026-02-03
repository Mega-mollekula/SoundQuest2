package com.example.soundquest2.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.soundquest2.core.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ThemeStorage(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val THEME = stringPreferencesKey("theme")
        const val DEFAULT_THEME = "LIGHT"
    }

    val themeFlow: Flow<AppTheme> = dataStore.data.map { prefs ->
        runCatching {
            AppTheme.valueOf(
                prefs[THEME] ?: DEFAULT_THEME
            )
        }.getOrElse { AppTheme.LIGHT }
    }

    suspend fun setTheme(theme: AppTheme) {
        dataStore.edit { it[THEME] = theme.name }
    }

    suspend fun getTheme(): AppTheme =
        themeFlow.first()
}