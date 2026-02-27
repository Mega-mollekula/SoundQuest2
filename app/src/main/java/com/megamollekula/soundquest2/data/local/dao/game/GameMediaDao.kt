package com.megamollekula.soundquest2.data.local.dao.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.megamollekula.soundquest2.data.local.entity.game.GameMediaEntity

@Dao
interface GameMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameMedia(media: List<GameMediaEntity>)

    @Query("UPDATE game_media SET local_video_path = :localVideoUri WHERE game_id = :gameId")
    suspend fun updateLocalVideoUri(gameId: Long, localVideoUri: String)

    @Query("UPDATE game_media SET local_audio_path = :localAudioUri WHERE game_id = :gameId")
    suspend fun updateLocalAudioUri(gameId: Long, localAudioUri: String)

    @Query("SELECT * FROM game_media WHERE local_audio_path IS NULL")
    suspend fun getGameMediaWithoutLocalAudioPath(): List<GameMediaEntity>

    @Query("SELECT * FROM game_media WHERE local_video_path IS NULL")
    suspend fun getGameMediaWithoutLocalVideoPath(): List<GameMediaEntity>
}