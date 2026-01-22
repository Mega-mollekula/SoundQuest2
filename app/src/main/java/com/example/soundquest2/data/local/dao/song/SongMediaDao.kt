package com.example.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundquest2.data.local.entity.song.SongMediaEntity

@Dao
interface SongMediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongMedia(clips: List<SongMediaEntity>)

    @Query("UPDATE song_media SET local_audio_path = :localAudioPath WHERE id = :mediaId")
    suspend fun updateSongMediaLocalPath(mediaId: Int, localAudioPath: String) // for songs downloading

    @Query("SELECT * FROM song_media WHERE local_audio_path IS NULL")
    suspend fun getSongMediaWithoutLocalAudioPath(): List<SongMediaEntity>

    @Query("SELECT * FROM song_media")
    suspend fun getAllClips(): List<SongMediaEntity>

    @Query("UPDATE song_media SET local_video_path = :localVideoPath WHERE id = :mediaId")
    fun updateSongMediaLocalVideoPath(mediaId: Int, localVideoPath: String)
}