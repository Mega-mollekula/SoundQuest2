package com.example.soundquest2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundquest2.domain.model.AppError
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.domain.model.DownloadProgress
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.usecase.DownloadMediaUseCase
import com.example.soundquest2.domain.usecase.LoadMediaUseCase
import com.example.soundquest2.ui.intent.MediaDownloadIntent
import com.example.soundquest2.ui.state.DownloadUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MediaDownloadViewModel(
    private val loadAudio: LoadMediaUseCase,
    private val downloadMedia: DownloadMediaUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DownloadUiState>(DownloadUiState.Idle)
    val uiState: StateFlow<DownloadUiState> = _uiState.asStateFlow()

    fun onIntent(intent: MediaDownloadIntent) {
        when (intent) {
            is MediaDownloadIntent.Retry -> {
                startDownload(
                    intent.gameMode,
                    intent.language,
                    intent.count
                )
            }

            is MediaDownloadIntent.StartDownload -> {
                startDownload(
                    intent.gameMode,
                    intent.language,
                    intent.count
                )
            }
        }
    }

    fun startDownload(gameMode: GameMode, language: String, count: Int) {

        if (_uiState.value is DownloadUiState.Downloading) return

        _uiState.update { DownloadUiState.Preparing }

        viewModelScope.launch {
            //getting metadata before downloading
            when(val result = loadAudio(forceRefresh = true, gameMode, language, count)) {

                is Result.Success -> {
                    downloadMedia().collect { result ->
                        when (result) {

                            is Result.Success -> {
                                handleProgress(result.data, gameMode, language, count)
                            }

                            is Result.Error -> {
                                _uiState.value = DownloadUiState.Error(
                                    result.error,
                                    gameMode,
                                    language,
                                    count
                                )
                            }
                        }
                    }
                }
                is Result.Error -> {
                    _uiState.update {
                        DownloadUiState.Error(
                            error = result.error,
                            gameMode = gameMode,
                            language = language,
                            count = count
                        )
                    }
                }
            }
        }
    }

    private fun handleProgress(
        progress: DownloadProgress,
        gameMode: GameMode,
        language: String,
        count: Int
    ) {
        when (progress) {

            is DownloadProgress.InProgress -> {
                _uiState.value = DownloadUiState.Downloading(
                    completed = progress.completed,
                    total = progress.total,
                    failed = progress.failed,
                    skipped = progress.skipped
                )
            }

            is DownloadProgress.Completed -> {
                if (progress.total > 0 && progress.completed == 0) {
                    _uiState.value = DownloadUiState.Error(
                        error = AppError.NoContent("Не удалось загрузить ни одного медиафайла"),
                        gameMode,
                        language,
                        count
                    )
                } else {
                    _uiState.value = DownloadUiState.Completed(
                        completed = progress.completed,
                        total = progress.total,
                        failed = progress.failed,
                        skipped = progress.skipped
                    )
                }
            }
        }
    }
}