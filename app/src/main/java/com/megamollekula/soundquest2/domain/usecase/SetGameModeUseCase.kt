package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.GameMode
import com.megamollekula.soundquest2.domain.model.GameState
import javax.inject.Inject

class SetGameModeUseCase @Inject constructor() {
    operator fun invoke(gameState: GameState, gameMode: GameMode): GameState {
        return gameState.copy(
            gameMode = gameMode
        )
    }
}