package com.megamollekula.soundquest2.dao

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.withTransaction
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.megamollekula.soundquest2.data.AppDatabase
import com.megamollekula.soundquest2.data.mapper.toEntities
import com.megamollekula.soundquest2.data.mapper.toGlobalFilmBundle
import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.data.remote.dto.films.FilmDto
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmDaoTest {

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
        val filmsDto: List<FilmDto> = ApiService.getAllFilms("ru")
        val bundles = filmsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalFilmBundle()

        db.withTransaction {
            db.filmDao().insertFilms(globalBundle.films)
            db.filmTranslationDao().insertFilmTranslations(globalBundle.translations)
            db.filmMediaDao().insertFilmMedia(globalBundle.media)
        }

        val filmFull = db.filmDao().getAllFilmsWithDetails()

        assertNotNull(filmFull)
        assertTrue(filmFull.isNotEmpty())

        filmFull.forEach { film ->
            Log.d("DaoTest", "  Film: ${film.film}")
            Log.d("DaoTest", "  Media: ${film.media}")
            Log.d("DaoTest", "  Translations: ${film.translations}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoGetRandomFilmsTest() = runTest {
        val filmsDto: List<FilmDto> = ApiService.getAllFilms("ru")
        val bundles = filmsDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalFilmBundle()

        db.withTransaction {
            db.filmDao().insertFilms(globalBundle.films)
            db.filmTranslationDao().insertFilmTranslations(globalBundle.translations)
            db.filmMediaDao().insertFilmMedia(globalBundle.media)
        }

        val filmFull = db.filmDao().getRandomFilms(2)

        assertNotNull(filmFull)
        assertTrue(filmFull.isNotEmpty())

        filmFull.forEach { film ->
            Log.d("DaoTest", "  Film: ${film.film}")
            Log.d("DaoTest", "  Media: ${film.media}")
            Log.d("DaoTest", "  Translations: ${film.translations}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }
}