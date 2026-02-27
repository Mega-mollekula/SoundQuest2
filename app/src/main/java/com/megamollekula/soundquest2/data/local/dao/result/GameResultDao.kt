package com.megamollekula.soundquest2.data.local.dao.result

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.megamollekula.soundquest2.data.local.entity.result.GameResultEntity

@Dao
interface GameResultDao {
    @Query("SELECT * FROM results")
    suspend fun getResults(): List<GameResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: GameResultEntity)

    @Query("SELECT * FROM results ORDER BY created_at DESC LIMIT 1")
    suspend fun getLastResult(): GameResultEntity
}