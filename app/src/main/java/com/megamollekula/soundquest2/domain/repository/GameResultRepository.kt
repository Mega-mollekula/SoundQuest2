package com.megamollekula.soundquest2.domain.repository

import com.megamollekula.soundquest2.domain.model.GameResult

interface GameResultRepository {
    suspend fun getResults(): List<GameResult>
    suspend fun insertResult(gameResult: GameResult): GameResult
    suspend fun getLastResult(): GameResult
}