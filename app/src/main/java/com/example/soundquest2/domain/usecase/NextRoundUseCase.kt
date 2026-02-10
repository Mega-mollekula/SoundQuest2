package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase

class NextRoundUseCase {

    operator fun invoke(gameState: GameState): GameState {
        return if (!gameState.isLastRound) {
            gameState.copy(
                gamePhase = GamePhase.ROUND,
                currentRoundIndex = gameState.currentRoundIndex + 1,
                selectedAnswer = null,
                isAnswerCorrect = null
            )
        } else {
            gameState.copy(
                gamePhase = GamePhase.FINISHED
            )
        }
    }
}