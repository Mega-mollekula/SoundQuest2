package com.example.soundquest2.ui.intent

sealed interface MediaDownloadIntent {
    data object CompletedClicked : MediaDownloadIntent
    data object RetryClicked : MediaDownloadIntent
    data object ExitClicked : MediaDownloadIntent
}