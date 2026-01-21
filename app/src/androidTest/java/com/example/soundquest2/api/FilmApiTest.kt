package com.example.soundquest2.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.soundquest2.data.remote.api.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmApiTest {

    @Test
    fun testGetAllFilms() = runTest {
        val films = ApiService.getAllFilms(language = "ru")

        Assert.assertNotNull("API returned null", films)

        Log.d("ApiTest", "Count of films: ${films.size}")

        Assert.assertTrue("List of films is empty", films.isNotEmpty())

        films.forEach { films ->
            Log.d("ApiTest", "  Film: $films")
            films.filmMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }

            Log.d("ApiTest", "  Type: ${films.filmType}")
            Log.d("ApiTest", "  Translation: ${films.filmTranslations}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }

    @Test
    fun testGetRandomFilms() = runTest {
        val films = ApiService.getRandomFilms(language = "ru", count = 2)

        Assert.assertNotNull("API returned null", films)

        Log.d("ApiTest", "Count of films: ${films.size}")

        Assert.assertTrue("List of films is empty", films.isNotEmpty())

        films.forEach { films ->
            Log.d("ApiTest", "  Film: $films")
            films.filmMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }

            Log.d("ApiTest", "  Type: ${films.filmType}")
            Log.d("ApiTest", "  Translation: ${films.filmTranslations}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }
}