package com.example.soundquest2.dao

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.withTransaction
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.data.mapper.toEntities
import com.example.soundquest2.data.mapper.toGlobalSongBundle
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.data.remote.dto.songs.SongDto
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongDaoTest {

    private lateinit var context: Context
    private lateinit var db: AppDatabase
    private lateinit var apiService: ApiService

    @Before
    fun setup() = runBlocking {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        apiService = ApiService
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun daoInsertAndReadTest() = runTest {
        val songsDto: List<SongDto> = ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
            db.songDao().insertSongs(globalBundle.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
        }

        val songsFull = db.songDao().getAllSongsWithDetails()

        assertNotNull(songsFull)
        assertTrue(songsFull.isNotEmpty())

        songsFull.forEach { song ->
            Log.d("DaoTest", "  Song: ${song.song}")
            Log.d("DaoTest", "  Media: ${song.visualMedia}")
            Log.d("DaoTest", "  Media: ${song.audioMedia}")
            Log.d("DaoTest", "  Artist: ${song.artist}")
            Log.d("DaoTest", "  Genre: ${song.song.genre}")
            Log.d("DaoTest", "  Decade: ${song.song.era}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoGetRandomSongsTest() = runTest {
        val songsDto: List<SongDto> =ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
            db.songDao().insertSongs(globalBundle.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
        }

        val songsFull = db.songDao().getRandomSongs(3)

        assertNotNull(songsFull)
        assertTrue(songsFull.isNotEmpty())

        songsFull.forEach { song ->
            Log.d("DaoTest", "  Song: ${song.song}")
            Log.d("DaoTest", "  Media: ${song.visualMedia}")
            Log.d("DaoTest", "  Media: ${song.audioMedia}")
            Log.d("DaoTest", "  Artist: ${song.artist}")
            Log.d("DaoTest", "  Genre: ${song.song.genre}")
            Log.d("DaoTest", "  Decade: ${song.song.era}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoGetSongsByGenreTest() = runTest {
        val songsDto: List<SongDto> = ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
            db.songDao().insertSongs(globalBundle.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
        }

        val songsFull = db.songDao().getSongsByGenre(genre = Genre.ROCK)

        assertNotNull(songsFull)
        assertTrue(songsFull.isNotEmpty())

        songsFull.forEach { song ->
            Log.d("DaoTest", "  Song: ${song.song}")
            Log.d("DaoTest", "  Media: ${song.visualMedia}")
            Log.d("DaoTest", "  Media: ${song.audioMedia}")
            Log.d("DaoTest", "  Artist: ${song.artist}")
            Log.d("DaoTest", "  Genre: ${song.song.genre}")
            Log.d("DaoTest", "  Decade: ${song.song.era}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoGetSongByEraTest() = runTest {
        val songsDto: List<SongDto> = ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
            db.songDao().insertSongs(globalBundle.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
        }

        val songsFull = db.songDao().getSongsByEra(Era.ERA_2000S)

        assertNotNull(songsFull)
        assertTrue(songsFull.isNotEmpty())

        songsFull.forEach { song ->
            Log.d("DaoTest", "  Song: ${song.song}")
            Log.d("DaoTest", "  Media: ${song.visualMedia}")
            Log.d("DaoTest", "  Media: ${song.audioMedia}")
            Log.d("DaoTest", "  Artist: ${song.artist}")
            Log.d("DaoTest", "  Genre: ${song.song.genre}")
            Log.d("DaoTest", "  Decade: ${song.song.era}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoLocalizationsTest() = runTest {
        val songsDto: List<SongDto> = ApiService.getAllSongs("ru")
        val bundles = songsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle.artistTranslations)
            db.songDao().insertSongs(globalBundle.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle.visualMedia)
        }

        val songsDto2: List<SongDto> = ApiService.getAllSongs("en")
        val bundles2 = songsDto2.map { it.toEntities() }

        val globalBundle2 = bundles2.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle2.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle2.artistTranslations)
            db.songDao().insertSongs(globalBundle2.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle2.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle2.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle2.visualMedia)
        }

        val songsDto3: List<SongDto> = ApiService.getAllSongs("en")
        val bundles3 = songsDto3.map { it.toEntities() }

        val globalBundle3 = bundles3.toGlobalSongBundle()

        db.withTransaction {
            db.artistDao().insertArtists(globalBundle3.artists)
            db.artistTranslationsDao().insertArtistTranslations(globalBundle3.artistTranslations)
            db.songDao().insertSongs(globalBundle3.songs)
            db.songTranslationsDao().insertSongTranslations(globalBundle3.songTranslations)
            db.songAudioMediaDao().insertAudioMedia(globalBundle3.audioMedia)
            db.songVisualMediaDao().insertVisualMedia(globalBundle3.visualMedia)
        }

        val songsFull = db.songDao().getSongsByEra(Era.ERA_2010S)

        assertNotNull(songsFull)
        assertTrue(songsFull.isNotEmpty())

        songsFull.forEach { song ->
            Log.d("DaoTest", "  Song: ${song.song}")
            Log.d("DaoTest", "  Media: ${song.visualMedia}")
            Log.d("DaoTest", "  Media: ${song.audioMedia}")
            Log.d("DaoTest", "  Artist: ${song.artist}")
            Log.d("DaoTest", "  Genre: ${song.song.genre}")
            Log.d("DaoTest", "  Decade: ${song.song.era}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }
}