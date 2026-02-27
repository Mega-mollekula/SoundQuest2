package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.GameMode
import com.megamollekula.soundquest2.domain.model.GameState
import com.megamollekula.soundquest2.domain.model.enums.GamePhase
import javax.inject.Inject

class ResetGameUseCase @Inject constructor() {

    operator fun invoke(gameState: GameState): GameState {
        return gameState.copy(
            rounds = emptyList(),
            currentRoundIndex = 0,
            score = 0,
            gamePhase = GamePhase.IDLE,
            isAnswerCorrect = null,
            selectedAnswer = null,
            gameMode = GameMode.FastStart
        )
    }
}