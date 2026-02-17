package com.example.soundquest2.di

import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.data.repository.FilmRepositoryImpl
import com.example.soundquest2.data.repository.GameRepositoryImpl
import com.example.soundquest2.data.repository.GameResultRepositoryImpl
import com.example.soundquest2.data.repository.SongRepositoryImpl
import com.example.soundquest2.domain.repository.FilmRepository
import com.example.soundquest2.domain.repository.GameRepository
import com.example.soundquest2.domain.repository.GameResultRepository
import com.example.soundquest2.domain.repository.SongRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSongRepository(
        apiService: ApiService,
        db: AppDatabase
    ): SongRepository {
        return SongRepositoryImpl(apiService, db)
    }

    @Provides
    @Singleton
    fun provideGameRepository(
        apiService: ApiService,
        db: AppDatabase
    ): GameRepository {
        return GameRepositoryImpl(apiService, db)
    }

    @Provides
    @Singleton
    fun provideFilmRepository(
        apiService: ApiService,
        db: AppDatabase
    ): FilmRepository {
        return FilmRepositoryImpl(apiService, db)
    }

    @Provides
    @Singleton
    fun provideGameResultRepository(
        db: AppDatabase
    ): GameResultRepository {
        return GameResultRepositoryImpl(db.gameResultDao())
    }
}
