package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.enums.GamePhase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FinishGameUseCase(
    private val insertResult: InsertResultUseCase
) {

    operator fun invoke(gameState: GameState, scope: CoroutineScope): GameState {

        val result = GameResult(
            createdAt = System.currentTimeMillis(),
            roundsCount = gameState.totalRounds,
            guessedSongsCount = gameState.score,
            gameMode = gameState.gameMode
        )

        scope.launch {
            insertResult(result)
        }

        return gameState.copy(
            gamePhase = GamePhase.FINISHED
        )
    }
}