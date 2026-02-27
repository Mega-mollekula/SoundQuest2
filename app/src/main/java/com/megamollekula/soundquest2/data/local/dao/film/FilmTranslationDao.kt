package com.megamollekula.soundquest2.data.local.dao.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.megamollekula.soundquest2.data.local.entity.film.FilmTranslationEntity

@Dao
interface FilmTranslationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmTranslations(translations: List<FilmTranslationEntity>)
}