package com.megamollekula.soundquest2.dao

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.withTransaction
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.megamollekula.soundquest2.data.AppDatabase
import com.megamollekula.soundquest2.data.mapper.toEntities
import com.megamollekula.soundquest2.data.mapper.toGlobalGameBundle
import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.data.remote.dto.games.GameDto
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameDaoTest {

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
        val gamesDto: List<GameDto> = ApiService.getAllGames("ru")
        val bundles = gamesDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle.translations)
            db.gameMediaDao().insertGameMedia(globalBundle.media)
        }

        val gameFull = db.gameDao().getAllGamesWithDetails()

        assertNotNull(gameFull)
        assertTrue(gameFull.isNotEmpty())

        gameFull.forEach { game ->
            Log.d("DaoTest", "  Song: ${game.game}")
            Log.d("DaoTest", "  Media: ${game.gameMedia}")
            Log.d("DaoTest", "  Translations: ${game.translations}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoRandomReadTest() = runTest {
        val gamesDto: List<GameDto> = ApiService.getAllGames("ru")
        val bundles = gamesDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle.translations)
            db.gameMediaDao().insertGameMedia(globalBundle.media)
        }

        val gameFull = db.gameDao().getRandomSongs(2)

        assertNotNull(gameFull)
        assertTrue(gameFull.isNotEmpty())

        gameFull.forEach { game ->
            Log.d("DaoTest", "  Song: ${game.game}")
            Log.d("DaoTest", "  Media: ${game.gameMedia}")
            Log.d("DaoTest", "  Translations: ${game.translations}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }

    @Test
    fun daoLocalizationsTest() = runTest {
        val gamesDto: List<GameDto> = ApiService.getAllGames("ru")
        val bundles = gamesDto.map { it.toEntities() }

        val globalBundle = bundles.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle.translations)
            db.gameMediaDao().insertGameMedia(globalBundle.media)
        }

        val gamesDto2: List<GameDto> = ApiService.getAllGames("en")
        val bundles2 = gamesDto2.map { it.toEntities() }

        val globalBundle2 = bundles2.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle2.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle2.translations)
            db.gameMediaDao().insertGameMedia(globalBundle2.media)
        }

        val gamesDto3: List<GameDto> = ApiService.getAllGames("en")
        val bundles3 = gamesDto3.map { it.toEntities() }

        val globalBundle3 = bundles3.toGlobalGameBundle()

        db.withTransaction {
            db.gameDao().insertGames(globalBundle3.games)
            db.gameTranslationDao().insertGameTranslations(globalBundle3.translations)
            db.gameMediaDao().insertGameMedia(globalBundle3.media)
        }

        val gameFull = db.gameDao().getAllGamesWithDetails()

        assertNotNull(gameFull)
        assertTrue(gameFull.isNotEmpty())

        gameFull.forEach { game ->
            Log.d("DaoTest", "  Song: ${game.game}")
            Log.d("DaoTest", "  Media: ${game.gameMedia}")
            Log.d("DaoTest", "  Translations: ${game.translations}")
            Log.d("DaoTest", "-------------------------------------------------")
        }
    }
}