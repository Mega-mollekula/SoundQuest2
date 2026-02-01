package com.example.soundquest2.ui.state

import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.content.MediaContent

sealed interface GameUiState {

    object Loading : GameUiState

    data class Round(
        val roundNumber: Int,
        val totalRounds: Int,
        val options: List<MediaContent>,
        val correctAnswer: MediaContent,
    ) : GameUiState

    data class Result(
        val isCorrect: Boolean,
        val selected: MediaContent,
        val correct: MediaContent,
    ) : GameUiState

    data class Finished(
        val gameResult: GameResult,
    ) : GameUiState

    data class Error(val error: AppError, val gameMode: GameMode) : GameUiState
}