package com.example.soundquest2.download

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.withTransaction
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.domain.model.DownloadProgress
import com.example.soundquest2.data.local.download.MediaDaos
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import com.example.soundquest2.data.local.storage.AndroidFileProvider
import com.example.soundquest2.data.mapper.toEntities
import com.example.soundquest2.data.mapper.toGlobalFilmBundle
import com.example.soundquest2.data.mapper.toGlobalGameBundle
import com.example.soundquest2.data.mapper.toGlobalSongBundle
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.data.remote.dto.films.FilmDto
import com.example.soundquest2.data.remote.dto.games.GameDto
import com.example.soundquest2.domain.repository.FileProvider
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DownloadTest {

    private lateinit var context: Context
    private lateinit var db: AppDatabase
    private lateinit var apiService: ApiService
    private lateinit var downloader: UnifiedMediaDownloader
    private lateinit var fileProvider: FileProvider
    private lateinit var daos: MediaDaos

    @Before
    fun setup() = runBlocking {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        apiService = ApiService
        fileProvider = AndroidFileProvider(context)
        daos = MediaDaos(
            filmMediaDao = db.filmMediaDao(),
            gameMediaDao = db.gameMediaDao(),
            songAudioMediaDao = db.songAudioMediaDao(),
            songVisualMediaDao = db.songVisualMediaDao()
        )
        downloader = UnifiedMediaDownloader(
            apiService,
            daos = daos,
            fileProvider.getAudioBaseDir(),
            fileProvider.getVideoBaseDir()
        )
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun downloadSongsMediaTest()  = runTest {
        val songsDto = ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.withTransaction {
                db.artistDao().insertArtists(globalBundle.artists)
                db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
                db.songDao().insertSongs(globalBundle.songs)
                db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
                db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
                db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
            }
        }

        val gamesDto: List<GameDto> = ApiService.getAllGames("ru")
        val bundles2 = gamesDto.map { it.toEntities() }

        val globalBundle2 = bundles2.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle2.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle2.translations)
            db.gameMediaDao().insertGameMedia(globalBundle2.media)
        }

        val filmsDto: List<FilmDto> = ApiService.getAllFilms("ru")
        val bundles3 = filmsDto.map { it.toEntities() }

        val globalBundle3 = bundles3.toGlobalFilmBundle()

        db.withTransaction {
            db.filmDao().insertFilms(globalBundle3.films)
            db.filmTranslationDao().insertFilmTranslations(globalBundle3.translations)
            db.filmMediaDao().insertFilmMedia(globalBundle3.media)
        }

        val progressEvents = mutableListOf<DownloadProgress>()
        downloader.downloadAll(parallelism = 3).toList(progressEvents)

        Log.d("DownloadTest", "=== Media Download Progress Log ===")
        progressEvents.forEach { event ->
            when (event) {
                is DownloadProgress.InProgress -> {
                    Log.d("DownloadTest", "Progress: ${event.completed}/${event.total} ✅ completed, ${event.failed} ❌ failed")
                }
                is DownloadProgress.Completed -> {
                    Log.d("DownloadTest", "🎉 COMPLETED: ${event.completed} succeeded, ${event.failed} failed out of ${event.total}")
                }
            }
        }

        val lastEvent = progressEvents.lastOrNull()
        assert(lastEvent is DownloadProgress.Completed)

        val songs = db.songDao().getAllSongsWithDetails()
        songs.forEach { song ->
            Log.d("DownloadTest", "  Media: ${song.visualMedia.localVideoPath}")
            Log.d("DownloadTest", "  Media: ${song.audioMedia}")
        }
    }
}