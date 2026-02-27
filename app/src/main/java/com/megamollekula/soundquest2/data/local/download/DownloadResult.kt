package com.megamollekula.soundquest2.data.local.download

sealed class DownloadResult {
    object Success : DownloadResult()
    data class Failed(val error: Throwable?) : DownloadResult()
    object Skipped : DownloadResult()
}
