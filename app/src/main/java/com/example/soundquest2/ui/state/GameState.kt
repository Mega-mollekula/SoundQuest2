package com.example.soundquest2.ui.state

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.Round

data class GameState(
    val rounds: List<Round> = emptyList(),
    val currentRoundIndex: Int = 0,
    val score: Int = 0,
    val gameMode: GameMode = GameMode.FastStart
) {
    val currentRound: Round?
        get() = rounds.getOrNull(currentRoundIndex)

    val isLastRound: Boolean
        get() = currentRoundIndex >= rounds.lastIndex

    val totalRounds: Int
        get() = rounds.size
}
