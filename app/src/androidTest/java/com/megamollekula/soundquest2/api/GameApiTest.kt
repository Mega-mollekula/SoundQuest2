package com.megamollekula.soundquest2.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.megamollekula.soundquest2.data.remote.api.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameApiTest {

    @Test
    fun testGetAllGames() = runTest {
        val games = ApiService.getAllGames(language = "ru")

        Assert.assertNotNull("API returned null", games)

        Log.d("ApiTest", "Count of games: ${games.size}")

        Assert.assertTrue("List of games is empty", games.isNotEmpty())

        games.forEach { games ->
            Log.d("ApiTest", "  Game: $games")
            Log.d("ApiTest", "  Media: ${games.gameMedia}")
            Log.d("ApiTest", "  Type: ${games.genre}")
            Log.d("ApiTest", "  Translation: ${games.gameTranslations}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }

    @Test
    fun testGetRandomGames() = runTest {
        val games = ApiService.getRandomGames(language = "ru", count = 2)

        Assert.assertNotNull("API returned null", games)

        Log.d("ApiTest", "Count of games: ${games.size}")

        Assert.assertTrue("List of games is empty", games.isNotEmpty())

        games.forEach { games ->
            Log.d("ApiTest", "  Game: $games")
            Log.d("ApiTest", "  Media: ${games.gameMedia}")
            Log.d("ApiTest", "  Type: ${games.genre}")
            Log.d("ApiTest", "  Translation: ${games.gameTranslations}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }
}