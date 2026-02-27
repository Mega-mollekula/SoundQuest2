package com.megamollekula.soundquest2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.megamollekula.soundquest2.data.local.datastore.languageDataStore
import com.megamollekula.soundquest2.data.local.datastore.themeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @Named("theme")
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.themeDataStore
    }

    @Provides
    @Singleton
    @Named("language")
    fun provideLanguageDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.languageDataStore
    }
}