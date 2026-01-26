package com.example.soundquest2.data.local.dao.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.soundquest2.data.local.entity.film.FilmEntity
import com.example.soundquest2.data.local.entity.film.relation.FilmWithDetails

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Transaction
    @Query ("SELECT * FROM films")
    suspend fun getAllFilmsWithDetails(): List<FilmWithDetails>

    @Transaction
    @Query ("SELECT * FROM films")
    suspend fun getRandomFilms(count: Int = 10): List<FilmWithDetails> {
        return getAllFilmsWithDetails().shuffled().take(count)
    }
}