package com.megamollekula.soundquest2.ui.intent

import com.megamollekula.soundquest2.domain.model.GameMode

sealed interface MediaDownloadIntent {
    data class Retry(
        val gameMode: GameMode,
        val count: Int
    ) : MediaDownloadIntent

    data class StartDownload(
        val gameMode: GameMode,
        val count: Int
    ) : MediaDownloadIntent
}