package com.megamollekula.soundquest2.domain.model

import com.megamollekula.soundquest2.domain.model.content.MediaContent
import com.megamollekula.soundquest2.domain.model.enums.GamePhase

data class GameState(
    val rounds: List<Round> = emptyList(),
    val currentRoundIndex: Int = 0,
    val score: Int = 0,
    val gameMode: GameMode = GameMode.FastStart,
    val gamePhase: GamePhase = GamePhase.IDLE,
    val error: AppError? = null,
    val selectedAnswer: MediaContent? = null,
    val isAnswerCorrect: Boolean? = null,
) {

    val currentRound: Round?
        get() = rounds.getOrNull(currentRoundIndex)

    val totalRounds: Int
        get() = rounds.size

    val isLastRound: Boolean
        get() = currentRoundIndex >= rounds.lastIndex
}