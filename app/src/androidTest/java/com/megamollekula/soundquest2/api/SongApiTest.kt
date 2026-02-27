package com.megamollekula.soundquest2.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.domain.model.enums.Era
import com.megamollekula.soundquest2.domain.model.enums.Genre
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongApiTest {

    @Test
    fun testGetAllSongs() = runTest {
        val songs = ApiService.getAllSongs(language = "en")

        Assert.assertNotNull("API returned null", songs)

        Log.d("ApiTest", "Count of songs: ${songs.size}")

        Assert.assertTrue("List of songs is empty", songs.isNotEmpty())

        songs.forEach { song ->
            Log.d("ApiTest", "  Song: $song")
            song.audioMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }
            Log.d(
                "ApiTest",
                "  Artist: ${song.artist})"
            )
            Log.d("ApiTest", "  Genre: ${song.genre}")
            Log.d("ApiTest", "  Decade: ${song.era}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }

    @Test
    fun testGetRandomSongs() = runTest {
        val songs = ApiService.getRandomSongs(language = "en", count = 2)

        Assert.assertNotNull("API returned null", songs)

        Log.d("ApiTest", "Count of songs: ${songs.size}")

        Assert.assertTrue("List of songs is empty", songs.isNotEmpty())

        songs.forEach { song ->
            Log.d("ApiTest", "  Song: $song")
            song.audioMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }
            Log.d(
                "ApiTest",
                "  Artist: ${song.artist})"
            )
            Log.d("ApiTest", "  Genre: ${song.genre}")
            Log.d("ApiTest", "  Decade: ${song.era}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }

    @Test
    fun testGetSongsByGenre() = runTest {
        val songs = ApiService.getSongsByGenre(language = "en", genre = Genre.ROCK)

        Assert.assertNotNull("API returned null", songs)

        Log.d("ApiTest", "Count of songs: ${songs.size}")

        Assert.assertTrue("List of songs is empty", songs.isNotEmpty())

        songs.forEach { song ->
            Log.d("ApiTest", "  Song: $song")
            song.audioMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }
            Log.d(
                "ApiTest",
                "  Artist: ${song.artist})"
            )
            Log.d("ApiTest", "  Genre: ${song.genre}")
            Log.d("ApiTest", "  Decade: ${song.era}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }

    @Test
    fun testGetSongsByEra() = runTest {
        val songs = ApiService.getSongsByEra(language = "en", era = Era.ERA_2010S)

        Assert.assertNotNull("API returned null", songs)

        Log.d("ApiTest", "Count of songs: ${songs.size}")

        Assert.assertTrue("List of songs is empty", songs.isNotEmpty())

        songs.forEach { song ->
            Log.d("ApiTest", "  Song: $song")
            song.audioMedia.forEach {
                Log.d("ApiTest", "  Clips: $it")
            }
            Log.d(
                "ApiTest",
                "  Artist: ${song.artist})"
            )
            Log.d("ApiTest", "  Genre: ${song.genre}")
            Log.d("ApiTest", "  Decade: ${song.era}")
            Log.d("ApiTest", "-------------------------------------------------")
        }
    }


}