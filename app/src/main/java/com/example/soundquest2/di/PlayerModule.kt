package com.example.soundquest2.di

import android.content.Context
import com.example.soundquest2.core.media.AudioPlayer
import com.example.soundquest2.core.media.ExoAudioPlayer
import com.example.soundquest2.core.media.ExoVideoPlayer
import com.example.soundquest2.core.media.MediaPlayer
import com.example.soundquest2.core.media.MenuExoPlayer
import com.example.soundquest2.core.media.VideoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun provideAudioPlayer(
        impl: ExoAudioPlayer
    ): AudioPlayer = impl

    @Provides
    @Singleton
    fun provideVideoPlayer(
        impl: ExoVideoPlayer
    ): VideoPlayer = impl

    @Provides
    @Singleton
    fun provideMenuMediaPlayer(
        @ApplicationContext context: Context
    ): MediaPlayer<String> {
        return MenuExoPlayer(context)
    }

    @Provides
    @Singleton
    fun provideExoVideoPlayer(
        @ApplicationContext context: Context,
    ): ExoVideoPlayer {
        return ExoVideoPlayer(context)
    }

    @Provides
    @Singleton
    fun provideExoAudioPlayer(
        @ApplicationContext context: Context,
    ): ExoAudioPlayer {
        return ExoAudioPlayer(context)
    }
}
