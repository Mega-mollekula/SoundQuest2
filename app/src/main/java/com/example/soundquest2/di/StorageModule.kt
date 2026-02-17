package com.example.soundquest2.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideThemeStorage(
        @Named("theme") dataStore: DataStore<Preferences>
    ): ThemeStorage = ThemeStorage(dataStore)

    @Provides
    @Singleton
    fun provideLanguageStorage(
        @Named("language") dataStore: DataStore<Preferences>
    ): LanguageStorage = LanguageStorage(dataStore)
}