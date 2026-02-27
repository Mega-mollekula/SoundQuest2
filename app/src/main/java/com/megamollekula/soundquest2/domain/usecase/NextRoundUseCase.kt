package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.GameState
import com.megamollekula.soundquest2.domain.model.enums.GamePhase
import javax.inject.Inject

class NextRoundUseCase @Inject constructor() {

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