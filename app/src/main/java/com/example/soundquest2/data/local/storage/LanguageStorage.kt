package com.example.soundquest2.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class LanguageStorage(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val LANGUAGE = stringPreferencesKey("language")
        const val DEFAULT_LANGUAGE = "ru"
    }

    suspend fun setLanguage(code: String) {
        dataStore.edit {
            it[LANGUAGE] = code
        }
    }

    suspend fun getLanguage(): String {
        return dataStore.data.first()[LANGUAGE] ?: DEFAULT_LANGUAGE
    }
}