package com.example.soundquest2.ui

import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase
import com.example.soundquest2.ui.state.GameUiState
import java.lang.Exception

fun GameState.toUiState(): GameUiState {
    return when (gamePhase) {

        GamePhase.IDLE -> GameUiState.Idle

        GamePhase.ROUND -> {
            val round = currentRound
                ?: return GameUiState.Error(
                    error = error ?: AppError.Unknown(
                        IllegalStateException("ROUND phase but currentRound == null")
                    )
                )

            GameUiState.Round(
                roundNumber = currentRoundIndex + 1,
                totalRounds = totalRounds,
                options = round.options,
            )
        }

        GamePhase.RESULT -> {
            val round = currentRound
                ?: return GameUiState.Error(
                    error = error ?: AppError.Unknown(
                        IllegalStateException("ROUND phase but currentRound == null")
                    )
                )

            GameUiState.Result(
                isCorrect = isAnswerCorrect ?: false,
                selected = selectedAnswer ?: round.correct,
                correct = round.correct
            )
        }

        GamePhase.FINISHED ->
            GameUiState.Finished(
                gameResult = GameResult(
                    createdAt = System.currentTimeMillis(),
                    roundsCount = totalRounds,
                    guessedSongsCount = score,
                    gameMode = gameMode
                )
            )

        GamePhase.ERROR ->
            GameUiState.Error(
                error = error ?: AppError.Unknown(
                    cause = Exception("Unknown error")
                )
            )
    }
}