package com.example.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundquest2.data.local.entity.song.SongAudioMediaEntity

@Dao
interface SongAudioMediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudioMedia(media: List<SongAudioMediaEntity>)

    @Query("UPDATE song_audio_media SET local_audio_path = :localAudioPath WHERE id = :audioId")
    suspend fun updateAudioMediaLocalPath(audioId: Int, localAudioPath: String) // for songs downloading

    @Query("SELECT * FROM song_audio_media WHERE local_audio_path IS NULL")
    suspend fun getAudioMediaWithoutLocalAudioPath(): List<SongAudioMediaEntity>

    @Query("SELECT * FROM song_audio_media")
    suspend fun getAllClips(): List<SongAudioMediaEntity>
}