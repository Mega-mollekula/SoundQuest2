package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase

class ResetGameUseCase {

    operator fun invoke(gameState: GameState): GameState {
        return gameState.copy(
            rounds = emptyList(),
            currentRoundIndex = 0,
            score = 0,
            gamePhase = GamePhase.IDLE,
            isAnswerCorrect = null,
            selectedAnswer = null
        )
    }
}