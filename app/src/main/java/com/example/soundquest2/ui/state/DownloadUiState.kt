package com.example.soundquest2.ui.state

import com.example.soundquest2.core.errors.AppError

sealed interface DownloadUiState {

    object Idle : DownloadUiState

    data class Downloading(
        val completed: Int,
        val total: Int,
        val failed: Int,
        val skipped: Int
    ) : DownloadUiState

    data class Completed(
        val completed: Int,
        val total: Int,
        val failed: Int,
        val skipped: Int
    ) : DownloadUiState

    data class Error(val error: AppError) : DownloadUiState
}