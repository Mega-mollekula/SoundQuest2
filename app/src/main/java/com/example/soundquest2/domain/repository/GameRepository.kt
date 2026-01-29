package com.example.soundquest2.domain.repository

import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.core.errors.Result

interface GameRepository {
    suspend fun getAllGames(forceRefresh: Boolean, language: String = "ru"): Result<List<Game>>
    suspend fun getRandomGames(forceRefresh: Boolean, count: Int = 20, language: String = "ru"): Result<List<Game>>
}