package com.example.soundquest2.data.local.dao.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundquest2.data.local.entity.film.FilmMediaEntity

@Dao
interface FilmMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilmMedia(media: List<FilmMediaEntity>)

    @Query("UPDATE film_media SET local_video_path = :localVideoPath WHERE film_id = :mediaId")
    suspend fun updateLocalVideoPath(mediaId: Long, localVideoPath: String)

    @Query("UPDATE film_media SET local_audio_path = :localAudioPath WHERE film_id = :filmId")
    suspend fun updateLocalAudioPath(filmId: Long, localAudioPath: String)

    @Query("SELECT * FROM film_media WHERE local_audio_path IS NULL")
    suspend fun getFilmMediaWithoutLocalAudioPath(): List<FilmMediaEntity>

    @Query("SELECT * FROM film_media WHERE local_video_path IS NULL")
    suspend fun getFilmMediaWithoutLocalVideoPath(): List<FilmMediaEntity>
}