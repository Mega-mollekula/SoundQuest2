package com.example.soundquest2.ui.state

import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.ui.model.UiMedia

sealed interface GameUiState {

    object Idle : GameUiState

    data class Round(
        val roundNumber: Int,
        val totalRounds: Int,
        val options: List<UiMedia>,
    ) : GameUiState

    data class Result(
        val isCorrect: Boolean,
        val selected: UiMedia,
        val correct: UiMedia,
    ) : GameUiState

    data class Finished(
        val gameResult: GameResult,
    ) : GameUiState

    data class Error(val error: AppError) : GameUiState
}