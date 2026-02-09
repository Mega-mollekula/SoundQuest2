package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.domain.model.enums.GamePhase

class StartGameUseCase(
    private val loadMedia: LoadMediaUseCase,
    private val generateRoundsUseCase: GenerateRoundsUseCase
) {
    suspend operator fun invoke(gameState: GameState, language: String, count: Int = 10): GameState {

        when (val result = loadMedia(false, gameState.gameMode, language, count)) {

            is Result.Success -> {
                val items = result.data

                val rounds = generateRoundsUseCase(
                    items = items,
                    roundsCount = 5,
                    optionsPerRound = 4
                )

                return gameState.copy(
                    gamePhase = GamePhase.ROUND,
                    rounds = rounds,
                    currentRoundIndex = 0,
                    score = 0
                )
            }

            is Result.Error -> {
                return gameState.copy(
                    gamePhase = GamePhase.ERROR,
                    error = result.error
                )
            }
        }
    }
}