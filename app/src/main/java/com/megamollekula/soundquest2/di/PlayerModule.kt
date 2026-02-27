package com.megamollekula.soundquest2.di

import android.content.Context
import com.megamollekula.soundquest2.ui.playback.player.AudioPlayer
import com.megamollekula.soundquest2.ui.playback.player.ExoAudioPlayer
import com.megamollekula.soundquest2.ui.playback.player.ExoVideoPlayer
import com.megamollekula.soundquest2.ui.playback.player.MediaPlayer
import com.megamollekula.soundquest2.ui.playback.player.MenuExoPlayer
import com.megamollekula.soundquest2.ui.playback.player.VideoPlayer
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
