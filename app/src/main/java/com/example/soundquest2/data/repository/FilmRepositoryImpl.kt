package com.example.soundquest2.data.repository

import androidx.room.withTransaction
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.repository.FilmRepository
import com.example.soundquest2.core.errors.Result
import com.example.soundquest2.data.local.entity.film.bundle.FilmGlobalEntitiesBundle
import com.example.soundquest2.data.mapper.toEntities
import com.example.soundquest2.data.mapper.toFilmModels
import com.example.soundquest2.data.mapper.toGlobalFilmBundle

class FilmRepositoryImpl(
    private val apiService: ApiService,
    private val db: AppDatabase,
) : FilmRepository, BaseMediaRepository() {

    override suspend fun getAllFilms(forceRefresh: Boolean, language: String): Result<List<Film>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.filmDao().getAllFilmsWithDetails() },
            remoteFetch = { apiService.getAllFilms(language) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalFilmBundle()
                insertFilmAudiosWithDetails(bundle)
            },
            mapToDomain = { it.toFilmModels() }
        )
    }

    override suspend fun getRandomFilms(forceRefresh: Boolean, count: Int, language: String): Result<List<Film>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.filmDao().getRandomFilms(count) },
            remoteFetch = { apiService.getRandomFilms(language, count) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalFilmBundle()
                insertFilmAudiosWithDetails(bundle)
            },
            mapToDomain = { it.toFilmModels() }
        )
    }

    private suspend fun insertFilmAudiosWithDetails(bundle: FilmGlobalEntitiesBundle) {
        db.withTransaction {
            db.filmDao().insertFilms(bundle.films)
            db.filmTranslationDao().insertFilmTranslations(bundle.translations)
            db.filmMediaDao().insertFilmMedia(bundle.media)
        }
    }
}