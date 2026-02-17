package com.example.soundquest2.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val LANGUAGE_DATASTORE_NAME = "language_store"
private const val THEME_DATASTORE_NAME = "theme_store"

val Context.languageDataStore: DataStore<Preferences> by preferencesDataStore(
    name = LANGUAGE_DATASTORE_NAME
)

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_DATASTORE_NAME
)