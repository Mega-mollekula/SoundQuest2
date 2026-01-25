package com.example.soundquest2.data.local.dao.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundquest2.data.local.entity.game.GameMediaEntity

@Dao
interface GameMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameMedia(media: List<GameMediaEntity>)

    @Query("UPDATE game_media SET local_video_path = :localVideoPath WHERE game_id = :mediaId")
    suspend fun updateLocalVideoPath(mediaId: Int, localVideoPath: String)

    @Query("UPDATE game_media SET local_audio_path = :localAudioPath WHERE game_id = :gameId")
    suspend fun updateLocalAudioPath(gameId: Int, localAudioPath: String)
}