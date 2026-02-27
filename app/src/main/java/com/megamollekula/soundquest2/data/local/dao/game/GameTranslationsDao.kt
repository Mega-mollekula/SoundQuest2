package com.megamollekula.soundquest2.data.local.dao.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.megamollekula.soundquest2.data.local.entity.game.GameTranslationEntity

@Dao
interface GameTranslationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameTranslations(translations: List<GameTranslationEntity>)
}