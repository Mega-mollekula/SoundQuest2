package com.example.soundquest2.data.local.download

sealed class DownloadProgress {

    data class InProgress(
        val completed: Int,
        val total: Int,
        val failed: Int,
        val skipped: Int
    ) : DownloadProgress()

    data class Completed(
        val completed: Int,
        val failed: Int,
        val total: Int,
        val skipped: Int
    ) : DownloadProgress()
}