package com.megamollekula.soundquest2.data.local.dao.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.megamollekula.soundquest2.data.local.entity.song.ArtistTranslationEntity

@Dao
interface ArtistTranslationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtistTranslations(translations: List<ArtistTranslationEntity>)
}