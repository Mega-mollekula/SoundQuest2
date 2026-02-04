package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.GameState

class SetGameModeUseCase {
    operator fun invoke(gameState: GameState, gameMode: GameMode): GameState {
        return gameState.copy(
            gameMode = gameMode
        )
    }
}