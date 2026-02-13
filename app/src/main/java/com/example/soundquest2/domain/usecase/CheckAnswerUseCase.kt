package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase

class CheckAnswerUseCase {
    operator fun invoke(gameState: GameState, id: Long): GameState {

        val isCorrect = id == gameState.currentRound!!.correct.id
        return gameState.copy(
            gamePhase = GamePhase.RESULT,
            isAnswerCorrect = isCorrect,
            score = if (isCorrect) gameState.score + 1 else gameState.score
        )
    }
}