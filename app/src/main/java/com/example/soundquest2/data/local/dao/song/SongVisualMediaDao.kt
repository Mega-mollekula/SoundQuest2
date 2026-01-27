package com.example.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundquest2.data.local.entity.song.SongVisualMediaEntity

@Dao
interface SongVisualMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVisualMedia(media: List<SongVisualMediaEntity>)

    @Query("UPDATE song_visual_media SET local_video_path = :localVideoPath WHERE song_id = :songId")
    fun updateLocalVideoPath(songId: Long, localVideoPath: String)

    @Query("SELECT * FROM song_visual_media WHERE local_video_path IS NULL")
    suspend fun getVisualMediaWithoutLocalVideoPath(): List<SongVisualMediaEntity>
}