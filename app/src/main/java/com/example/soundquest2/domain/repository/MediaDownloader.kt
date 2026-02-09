package com.example.soundquest2.domain.repository

import com.example.soundquest2.data.local.download.DownloadProgress
import kotlinx.coroutines.flow.Flow

interface MediaDownloader {
    fun downloadAll(parallelism: Int = 3): Flow<DownloadProgress>
}