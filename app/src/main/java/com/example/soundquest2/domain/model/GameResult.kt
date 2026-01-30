package com.example.soundquest2.domain.model

data class GameResult(
    val createdAt: Long = System.currentTimeMillis(),
    val roundsCount: Int,
    val guessedSongsCount: Int,
    val gameMode: GameMode
)