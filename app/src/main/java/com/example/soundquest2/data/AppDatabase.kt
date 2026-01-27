package com.example.soundquest2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soundquest2.data.local.dao.film.FilmDao
import com.example.soundquest2.data.local.dao.film.FilmMediaDao
import com.example.soundquest2.data.local.dao.film.FilmTranslationDao
import com.example.soundquest2.data.local.dao.game.GameDao
import com.example.soundquest2.data.local.dao.game.GameMediaDao
import com.example.soundquest2.data.local.dao.game.GameTranslationsDao
import com.example.soundquest2.data.local.dao.song.ArtistDao
import com.example.soundquest2.data.local.dao.song.ArtistTranslationsDao
import com.example.soundquest2.data.local.dao.song.SongAudioMediaDao
import com.example.soundquest2.data.local.dao.song.SongDao
import com.example.soundquest2.data.local.dao.song.SongTranslationsDao
import com.example.soundquest2.data.local.dao.song.SongVisualMediaDao
import com.example.soundquest2.data.local.entity.film.FilmEntity
import com.example.soundquest2.data.local.entity.film.FilmMediaEntity
import com.example.soundquest2.data.local.entity.film.FilmTranslationEntity
import com.example.soundquest2.data.local.entity.game.GameEntity
import com.example.soundquest2.data.local.entity.game.GameMediaEntity
import com.example.soundquest2.data.local.entity.game.GameTranslationEntity
import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongVisualMediaEntity

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
        FilmMediaEntity::class
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


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                )
                    .fallbackToDestructiveMigration(true)// delete files
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}