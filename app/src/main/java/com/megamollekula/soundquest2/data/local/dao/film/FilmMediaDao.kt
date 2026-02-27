package com.megamollekula.soundquest2.data.local.dao.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.megamollekula.soundquest2.data.local.entity.film.FilmMediaEntity

@Dao
interface FilmMediaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilmMedia(media: List<FilmMediaEntity>)

    @Query("UPDATE film_media SET local_video_path = :localVideoUri WHERE film_id = :filmId")
    suspend fun updateLocalVideoUri(filmId: Long, localVideoUri: String)

    @Query("UPDATE film_media SET local_audio_path = :localAudioUri WHERE film_id = :filmId")
    suspend fun updateLocalAudioUri(filmId: Long, localAudioUri: String)

    @Query("SELECT * FROM film_media WHERE local_audio_path IS NULL")
    suspend fun getFilmMediaWithoutLocalAudioPath(): List<FilmMediaEntity>

    @Query("SELECT * FROM film_media WHERE local_video_path IS NULL")
    suspend fun getFilmMediaWithoutLocalVideoPath(): List<FilmMediaEntity>
}