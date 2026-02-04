package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase
import kotlinx.coroutines.CoroutineScope

class NextRoundUseCase(
    private val finishGame: FinishGameUseCase
) {

    operator fun invoke(gameState: GameState, scope: CoroutineScope): GameState {

        return if (!gameState.isLastRound) {
            gameState.copy(
                gamePhase = GamePhase.ROUND,
                currentRoundIndex = gameState.currentRoundIndex + 1,
                selectedAnswer = null,
                isAnswerCorrect = null
            )
        } else {
            finishGame(gameState, scope)
        }
    }
}