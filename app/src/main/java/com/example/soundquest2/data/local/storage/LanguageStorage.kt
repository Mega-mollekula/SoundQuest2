package com.example.soundquest2.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.soundquest2.core.language.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LanguageStorage(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val LANGUAGE = stringPreferencesKey("language")
        const val DEFAULT_LANGUAGE = "ru"
    }

    val languageFlow: Flow<AppLanguage> =
        dataStore.data.map { prefs ->
            AppLanguage.fromCode(
                prefs[LANGUAGE] ?: DEFAULT_LANGUAGE
            )
        }

    suspend fun setLanguage(language: AppLanguage) {
        dataStore.edit {
            it[LANGUAGE] = language.code
        }
    }

    suspend fun getLanguage(): AppLanguage {
        return AppLanguage.fromCode(
            dataStore.data.first()[LANGUAGE] ?: DEFAULT_LANGUAGE
        )
    }
}