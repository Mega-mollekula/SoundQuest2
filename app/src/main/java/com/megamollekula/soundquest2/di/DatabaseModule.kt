package com.megamollekula.soundquest2.di

import android.content.Context
import androidx.room.Room
import com.megamollekula.soundquest2.data.AppDatabase
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
import com.megamollekula.soundquest2.data.local.download.MediaDaos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).fallbackToDestructiveMigration(true)
        .build()
    }

    @Provides fun provideSongDao(db: AppDatabase): SongDao = db.songDao()
    @Provides fun provideArtistDao(db: AppDatabase): ArtistDao = db.artistDao()
    @Provides fun provideArtistTranslationsDao(db: AppDatabase): ArtistTranslationsDao = db.artistTranslationsDao()
    @Provides fun provideSongTranslationsDao(db: AppDatabase): SongTranslationsDao = db.songTranslationsDao()
    @Provides fun provideSongAudioMediaDao(db: AppDatabase): SongAudioMediaDao = db.songAudioMediaDao()
    @Provides fun provideSongVisualMediaDao(db: AppDatabase): SongVisualMediaDao = db.songVisualMediaDao()

    @Provides fun provideGameDao(db: AppDatabase): GameDao = db.gameDao()
    @Provides fun provideGameTranslationsDao(db: AppDatabase): GameTranslationsDao = db.gameTranslationDao()
    @Provides fun provideGameMediaDao(db: AppDatabase): GameMediaDao = db.gameMediaDao()

    @Provides fun provideFilmDao(db: AppDatabase): FilmDao = db.filmDao()
    @Provides fun provideFilmTranslationDao(db: AppDatabase): FilmTranslationDao = db.filmTranslationDao()
    @Provides fun provideFilmMediaDao(db: AppDatabase): FilmMediaDao = db.filmMediaDao()

    @Provides fun provideGameResultDao(db: AppDatabase): GameResultDao = db.gameResultDao()

    @Provides
    @Singleton
    fun provideMediaDaos(db: AppDatabase): MediaDaos {
        return MediaDaos(
            filmMediaDao = db.filmMediaDao(),
            gameMediaDao = db.gameMediaDao(),
            songAudioMediaDao = db.songAudioMediaDao(),
            songVisualMediaDao = db.songVisualMediaDao()
        )
    }
}