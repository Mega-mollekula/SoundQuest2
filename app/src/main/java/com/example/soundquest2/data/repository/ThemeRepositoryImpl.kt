package com.example.soundquest2.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.soundquest2.domain.model.enums.AppTheme
import com.example.soundquest2.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl (private val dataStore: DataStore<Preferences>): ThemeRepository {

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    override fun observe(): Flow<AppTheme> = dataStore.data.map { prefs ->
        when (prefs[THEME_KEY]) {
            AppTheme.DARK.name -> AppTheme.DARK
            AppTheme.NEW_YEAR.name -> AppTheme.NEW_YEAR
            else -> AppTheme.LIGHT
        }
    }

    override suspend fun saveTheme(theme: AppTheme) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }

    override suspend fun getInitialTheme(): AppTheme {
        val prefs = dataStore.data.first()
        return when (prefs[THEME_KEY]) {
            AppTheme.DARK.name -> AppTheme.DARK
            AppTheme.NEW_YEAR.name -> AppTheme.NEW_YEAR
            else -> AppTheme.LIGHT
        }
    }
}