package com.megamollekula.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.megamollekula.soundquest2.data.local.entity.song.SongTranslationEntity

@Dao
interface SongTranslationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongTranslations(translations: List<SongTranslationEntity>)
}