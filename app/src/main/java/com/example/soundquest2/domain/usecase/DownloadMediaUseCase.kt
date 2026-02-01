package com.example.soundquest2.domain.usecase

import com.example.soundquest2.data.local.download.DownloadProgress
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import kotlinx.coroutines.flow.Flow

class DownloadMediaUseCase(
    private val downloader: UnifiedMediaDownloader
) {
    operator fun invoke(): Flow<DownloadProgress> {
        return downloader.downloadAll()
    }
}