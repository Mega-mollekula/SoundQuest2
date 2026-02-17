package com.example.soundquest2.di

import android.content.Context
import com.example.soundquest2.data.local.storage.AndroidFileProvider
import com.example.soundquest2.domain.repository.FileProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @Provides
    @Singleton
    fun provideFileProvider(
        @ApplicationContext context: Context
    ): FileProvider {
        return AndroidFileProvider(context)
    }

    @Provides
    @Singleton
    fun provideAndroidFileProvider(@ApplicationContext context: Context): AndroidFileProvider {
        return AndroidFileProvider(context)
    }
}