package com.example.soundquest2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soundquest2.data.local.dao.song.ArtistDao
import com.example.soundquest2.data.local.dao.song.ArtistTranslationsDao
import com.example.soundquest2.data.local.dao.song.SongDao
import com.example.soundquest2.data.local.dao.song.SongMediaDao
import com.example.soundquest2.data.local.dao.song.SongTranslationsDao
import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.SongMediaEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity

@Database(
    entities = [
        ArtistEntity::class,
        SongEntity::class,
        SongMediaEntity::class,
        SongTranslationEntity::class,
        ArtistTranslationEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun artistTranslationsDao(): ArtistTranslationsDao
    abstract fun songTranslationsDao(): SongTranslationsDao
    abstract fun artistDao(): ArtistDao
    abstract fun songMediaDao(): SongMediaDao

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