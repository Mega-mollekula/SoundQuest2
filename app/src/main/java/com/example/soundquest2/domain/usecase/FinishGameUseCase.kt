package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.GameState
import javax.inject.Inject

class FinishGameUseCase @Inject constructor() {

    operator fun invoke(gameState: GameState): GameResult {
        return GameResult(
            createdAt = System.currentTimeMillis(),
            roundsCount = gameState.totalRounds,
            guessedSongsCount = gameState.score,
            gameMode = gameState.gameMode
        )
    }
}