package com.megamollekula.soundquest2.domain.repository

import com.megamollekula.soundquest2.domain.model.DownloadProgress
import kotlinx.coroutines.flow.Flow

interface MediaDownloader {
    fun downloadAll(parallelism: Int = 3): Flow<DownloadProgress>
}