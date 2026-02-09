package com.example.soundquest2.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.domain.model.DownloadProgress
import com.example.soundquest2.data.local.download.MediaDaos
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import com.example.soundquest2.data.local.storage.AndroidFileProvider
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.data.repository.GameRepositoryImpl
import com.example.soundquest2.domain.repository.FileProvider
import com.example.soundquest2.domain.repository.GameRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameRepositoryTest {

    private lateinit var context: Context
    private lateinit var db: AppDatabase
    private lateinit var apiService: ApiService
    private lateinit var downloader: UnifiedMediaDownloader
    private lateinit var fileProvider: FileProvider
    private lateinit var daos: MediaDaos
    private lateinit var repository: GameRepository

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
        repository = GameRepositoryImpl(apiService, db)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun repositoryTest() = runTest {
        val gamesEn = repository.getAllGames(true, "en")
        val progressEvents = mutableListOf<DownloadProgress>()
        downloader.downloadAll(parallelism = 3).toList(progressEvents)
        val gamesRu = repository.getAllGames(true, "ru")
        downloader.downloadAll(parallelism = 3).toList(progressEvents)
        val result2 = repository.getAllGames(false, "ru")

        when (result2) {
            is Result.Success -> {
                assertTrue("Empty list returned", result2.data.isNotEmpty())

                result2.data.forEach { game ->
                    assertNotNull("Media doesn't have local path", game.gameMedia.localAudioPath)
                }
                result2.data.forEach { game ->
                    Log.d("RepositoryTest", "  Game Translations: ${game.gameTranslations}")
                    Log.d("RepositoryTest", "  Media: ${game.gameMedia}")
                    Log.d("RepositoryTest", "  Developer: ${game.developer}")
                    Log.d("RepositoryTest", "  Publisher: ${game.publisher}")
                    Log.d("RepositoryTest", "-------------------------------------------------")
                }
            }

            is Result.Error -> {
                Log.d("RepositoryTest", "  ERROR: ${result2.error::class.simpleName}")
            }
        }
    }
}