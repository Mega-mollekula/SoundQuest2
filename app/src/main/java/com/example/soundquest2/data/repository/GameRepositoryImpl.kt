package com.example.soundquest2.data.repository

import androidx.room.withTransaction
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.data.local.entity.game.bundle.GameGlobalEntitiesBundle
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.domain.repository.GameRepository
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.data.mapper.toEntities
import com.example.soundquest2.data.mapper.toGameModels
import com.example.soundquest2.data.mapper.toGlobalGameBundle
import com.example.soundquest2.domain.model.content.Game

class GameRepositoryImpl(
    private val apiService: ApiService,
    private val db: AppDatabase,
) : GameRepository, BaseMediaRepository() {

    override suspend fun getAllGames(forceRefresh: Boolean, language: String): Result<List<Game>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.gameDao().getAllGamesWithDetails() },
            remoteFetch = { apiService.getAllGames(language) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalGameBundle()
                insertGameAudiosWithDetails(bundle)
            },
            mapToDomain = { it.toGameModels() }
        )
    }

    override suspend fun getRandomGames(forceRefresh: Boolean, count: Int, language: String): Result<List<Game>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.gameDao().getRandomSongs(count) },
            remoteFetch = { apiService.getRandomGames(language, count) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalGameBundle()
                insertGameAudiosWithDetails(bundle)
            },
            mapToDomain = { it.toGameModels() }
        )
    }

    private suspend fun insertGameAudiosWithDetails(bundle: GameGlobalEntitiesBundle) {
        db.withTransaction {
            db.gameDao().insertGames(bundle.games)
            db.gameTranslationDao().insertGameTranslations(bundle.translations)
            db.gameMediaDao().insertGameMedia(bundle.media)
        }
    }
}