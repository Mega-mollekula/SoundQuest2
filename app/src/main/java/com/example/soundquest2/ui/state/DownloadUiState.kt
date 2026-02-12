package com.example.soundquest2.ui.state

import com.example.soundquest2.domain.model.AppError
import com.example.soundquest2.domain.model.GameMode

sealed interface DownloadUiState {

    object Idle : DownloadUiState

    object Preparing : DownloadUiState

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

    data class Error(
        val error: AppError,
        val gameMode: GameMode,
        val language: String,
        val count: Int
    ) : DownloadUiState
}