package com.megamollekula.soundquest2.data.local.dao.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.megamollekula.soundquest2.data.local.entity.game.GameEntity
import com.megamollekula.soundquest2.data.local.entity.game.relation.GameWithDetails

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGames(games: List<GameEntity>)

    @Transaction
    @Query ("SELECT * FROM games")
    suspend fun getAllGamesWithDetails(): List<GameWithDetails>

    @Transaction
    @Query ("SELECT * FROM games")
    suspend fun getRandomSongs(count: Int = 10): List<GameWithDetails> {
        return getAllGamesWithDetails().shuffled().take(count)
    }
}