package com.megamollekula.soundquest2.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.megamollekula.soundquest2.domain.model.enums.AppTheme
import kotlinx.coroutines.flow.first

class ThemeStorage(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val THEME = stringPreferencesKey("theme")
        const val DEFAULT_THEME = "LIGHT"
    }

    suspend fun setTheme(theme: AppTheme) {
        dataStore.edit {
            it[THEME] = theme.name
        }
    }

    suspend fun getTheme(): AppTheme {
        return runCatching {
            AppTheme.valueOf(
                dataStore.data.first()[THEME] ?: DEFAULT_THEME
            )
        }.getOrElse { AppTheme.LIGHT }
    }
}