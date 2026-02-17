package com.example.soundquest2.di

import com.example.soundquest2.data.local.download.MediaDaos
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.domain.repository.FileProvider
import com.example.soundquest2.domain.repository.MediaDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloaderModule {

    @Provides
    @Singleton
    fun provideMediaDownloader(
        apiService: ApiService,
        mediaDaos: MediaDaos,
        fileProvider: FileProvider
    ): MediaDownloader {
        return UnifiedMediaDownloader(
            apiService = apiService,
            daos = mediaDaos,
            audioDir = fileProvider.getAudioBaseDir(),
            videoDir = fileProvider.getVideoBaseDir()
        )
    }
}