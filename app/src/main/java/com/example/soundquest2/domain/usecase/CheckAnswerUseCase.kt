package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.enums.GamePhase

class CheckAnswerUseCase {
    operator fun invoke(gameState: GameState, selected: MediaContent): GameState {

        val isCorrect = selected == gameState.currentRound!!.correct
        return gameState.copy(
            gamePhase = GamePhase.RESULT,
            score = if (isCorrect) gameState.score + 1 else gameState.score
        )
    }
}