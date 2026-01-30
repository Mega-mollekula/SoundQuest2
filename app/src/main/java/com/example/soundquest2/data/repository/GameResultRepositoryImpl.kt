package com.example.soundquest2.data.repository

import com.example.soundquest2.data.local.dao.result.GameResultDao
import com.example.soundquest2.data.mapper.toResultEntity
import com.example.soundquest2.data.mapper.toResultModel
import com.example.soundquest2.data.mapper.toResultModels
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.repository.GameResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameResultRepositoryImpl(private val dao: GameResultDao): GameResultRepository {
    override suspend fun getResults(): List<GameResult> = withContext(Dispatchers.IO){
        dao.getResults().toResultModels()
    }

    override suspend fun insertResult(gameResult: GameResult): GameResult = withContext(Dispatchers.IO){
        dao.insertResult(gameResult.toResultEntity())
        gameResult
    }

    override suspend fun getLastResult(): GameResult {
        return dao.getLastResult().toResultModel()
    }
}