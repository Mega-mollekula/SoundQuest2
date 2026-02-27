package com.megamollekula.soundquest2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.megamollekula.soundquest2.data.local.dao.film.FilmDao
import com.megamollekula.soundquest2.data.local.dao.film.FilmMediaDao
import com.megamollekula.soundquest2.data.local.dao.film.FilmTranslationDao
import com.megamollekula.soundquest2.data.local.dao.game.GameDao
import com.megamollekula.soundquest2.data.local.dao.game.GameMediaDao
import com.megamollekula.soundquest2.data.local.dao.game.GameTranslationsDao
import com.megamollekula.soundquest2.data.local.dao.result.GameResultDao
import com.megamollekula.soundquest2.data.local.dao.song.ArtistDao
import com.megamollekula.soundquest2.data.local.dao.song.ArtistTranslationsDao
import com.megamollekula.soundquest2.data.local.dao.song.SongAudioMediaDao
import com.megamollekula.soundquest2.data.local.dao.song.SongDao
import com.megamollekula.soundquest2.data.local.dao.song.SongTranslationsDao
import com.megamollekula.soundquest2.data.local.dao.song.SongVisualMediaDao
import com.megamollekula.soundquest2.data.local.entity.film.FilmEntity
import com.megamollekula.soundquest2.data.local.entity.film.FilmMediaEntity
import com.megamollekula.soundquest2.data.local.entity.film.FilmTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameMediaEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.result.GameResultEntity
import com.megamollekula.soundquest2.data.local.entity.song.ArtistEntity
import com.megamollekula.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongVisualMediaEntity

@Database(
    entities = [
        ArtistEntity::class,
        SongEntity::class,
        SongAudioMediaEntity::class,
        SongVisualMediaEntity::class,
        SongTranslationEntity::class,
        ArtistTranslationEntity::class,
        GameEntity::class,
        GameTranslationEntity::class,
        GameMediaEntity::class,
        FilmEntity::class,
        FilmTranslationEntity::class,
        FilmMediaEntity::class,
        GameResultEntity::class
    ],
    version = 3
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun artistTranslationsDao(): ArtistTranslationsDao
    abstract fun songTranslationsDao(): SongTranslationsDao
    abstract fun artistDao(): ArtistDao
    abstract fun songAudioMediaDao(): SongAudioMediaDao
    abstract fun songVisualMediaDao(): SongVisualMediaDao

    abstract fun gameDao(): GameDao
    abstract fun gameTranslationDao(): GameTranslationsDao
    abstract fun gameMediaDao(): GameMediaDao

    abstract fun filmDao(): FilmDao
    abstract fun filmTranslationDao(): FilmTranslationDao
    abstract fun filmMediaDao(): FilmMediaDao
    abstract fun gameResultDao(): GameResultDao
}