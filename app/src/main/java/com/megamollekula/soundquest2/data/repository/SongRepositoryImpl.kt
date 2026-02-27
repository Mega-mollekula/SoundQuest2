package com.megamollekula.soundquest2.data.repository

import androidx.room.withTransaction
import com.megamollekula.soundquest2.data.AppDatabase
import com.megamollekula.soundquest2.data.local.entity.song.bundle.SongGlobalBundle
import com.megamollekula.soundquest2.data.mapper.toEntities
import com.megamollekula.soundquest2.data.mapper.toGlobalSongBundle
import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.domain.model.enums.Genre
import com.megamollekula.soundquest2.domain.model.content.Song
import com.megamollekula.soundquest2.domain.repository.SongRepository
import com.megamollekula.soundquest2.domain.model.Result
import com.megamollekula.soundquest2.data.mapper.toSongModels
import com.megamollekula.soundquest2.domain.model.enums.Era

class SongRepositoryImpl(
    private val apiService: ApiService,
    private val db: AppDatabase,
) : SongRepository, BaseMediaRepository() {

    override suspend fun getAllSongs(forceRefresh: Boolean, language: String): Result<List<Song>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.songDao().getAllSongsWithDetails() },
            remoteFetch = { apiService.getAllSongs(language) },
            saveRemote = { remote ->
                val bundle = remote.map {it.toEntities()}.toGlobalSongBundle()
                insertSongsWithDetails(bundle)
            },
            mapToDomain = { it.toSongModels() }
        )
    }

    override suspend fun getRandomSongs(forceRefresh: Boolean, language: String, count: Int): Result<List<Song>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.songDao().getRandomSongs(count) },
            remoteFetch = { apiService.getRandomSongs(language, count) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalSongBundle()
                insertSongsWithDetails(bundle)
            },
            mapToDomain = { it.toSongModels() }
        )
    }

    override suspend fun getSongsByGenre(genre: Genre, forceRefresh: Boolean, language: String,): Result<List<Song>>{
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.songDao().getSongsByGenre(genre) },
            remoteFetch = { apiService.getSongsByGenre(language, genre) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalSongBundle()
                insertSongsWithDetails(bundle)
            },
            mapToDomain = { it.toSongModels() }
        )
    }

    override suspend fun getSongsByEra(era: Era, forceRefresh: Boolean, language: String): Result<List<Song>> {
        return fetchData(
            forceRefresh = forceRefresh,
            localFetch = { db.songDao().getSongsByEra(era) },
            remoteFetch = { apiService.getSongsByEra(language, era) },
            saveRemote = { remote ->
                val bundle = remote.map{it.toEntities()}.toGlobalSongBundle()
                insertSongsWithDetails(bundle)
            },
            mapToDomain = { it.toSongModels() }
        )
    }

    private suspend fun insertSongsWithDetails(bundle: SongGlobalBundle) {
        db.withTransaction {
            db.artistDao().insertArtists(bundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(bundle.artistTranslations)
            db.songDao().insertSongs(bundle.songs)
            db.songTranslationsDao().insertSongTranslations(bundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(bundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(bundle.visualMedia)
        }
    }
}