package com.example.soundquest2.ui.intent

sealed interface MediaDownloadIntent {
    data object Retry : MediaDownloadIntent
}