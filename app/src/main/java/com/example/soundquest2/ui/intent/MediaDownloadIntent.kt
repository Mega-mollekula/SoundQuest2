package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.GameMode

sealed interface MediaDownloadIntent {
    data class Retry(
        val gameMode: GameMode,
        val language: String,
        val count: Int
    ) : MediaDownloadIntent

    data class StartDownload(
        val gameMode: GameMode,
        val language: String,
        val count: Int
    ) : MediaDownloadIntent
}