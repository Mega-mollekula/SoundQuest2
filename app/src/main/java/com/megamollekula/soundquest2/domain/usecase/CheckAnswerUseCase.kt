package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.GameState
import com.megamollekula.soundquest2.domain.model.enums.GamePhase
import javax.inject.Inject

class CheckAnswerUseCase @Inject constructor() {
    operator fun invoke(gameState: GameState, id: Long): GameState {

        val isCorrect = id == gameState.currentRound!!.correct.id
        return gameState.copy(
            gamePhase = GamePhase.RESULT,
            isAnswerCorrect = isCorrect,
            score = if (isCorrect) gameState.score + 1 else gameState.score
        )
    }
}