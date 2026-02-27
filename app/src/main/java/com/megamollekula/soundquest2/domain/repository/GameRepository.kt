package com.megamollekula.soundquest2.domain.repository

import com.megamollekula.soundquest2.domain.model.content.Game
import com.megamollekula.soundquest2.domain.model.Result

interface GameRepository {
    suspend fun getAllGames(forceRefresh: Boolean, language: String = "ru"): Result<List<Game>>
    suspend fun getRandomGames(forceRefresh: Boolean, count: Int = 20, language: String = "ru"): Result<List<Game>>
}