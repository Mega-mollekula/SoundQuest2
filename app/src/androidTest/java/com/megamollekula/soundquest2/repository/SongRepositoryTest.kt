package com.megamollekula.soundquest2.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.megamollekula.soundquest2.data.AppDatabase
import com.megamollekula.soundquest2.data.local.download.MediaDaos
import com.megamollekula.soundquest2.data.local.download.UnifiedMediaDownloader
import com.megamollekula.soundquest2.data.local.storage.AndroidFileProvider
import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.data.repository.SongRepositoryImpl
import com.megamollekula.soundquest2.domain.repository.FileProvider
import com.megamollekula.soundquest2.domain.repository.SongRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.megamollekula.soundquest2.domain.model.Result
import com.megamollekula.soundquest2.domain.model.DownloadProgress
import kotlinx.coroutines.flow.toList

@RunWith(AndroidJUnit4::class)
class SongRepositoryTest {

    private lateinit var context: Context
    private lateinit var db: AppDatabase
    private lateinit var apiService: ApiService
    private lateinit var downloader: UnifiedMediaDownloader
    private lateinit var fileProvider: FileProvider
    private lateinit var daos: MediaDaos
    private lateinit var repository: SongRepository

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
        repository = SongRepositoryImpl(apiService, db)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun repositoryTest() = runTest {
        val songsEn = repository.getAllSongs(true, "en")
        val progressEvents = mutableListOf<DownloadProgress>()
        downloader.downloadAll(parallelism = 3).toList(progressEvents)
        val songsRu = repository.getAllSongs(true, "ru")
        downloader.downloadAll(parallelism = 3).toList(progressEvents)
        val result2 = repository.getAllSongs(false, "ru")

        when (result2) {
            is Result.Success -> {
                assertTrue("Empty list returned", result2.data.isNotEmpty())

                result2.data.forEach { song ->
                    song.audioMedia.forEach { audioMedia ->
                        assertNotNull("Clip doesn't have local path", audioMedia.localAudioPath)
                    }
                }
                result2.data.forEach { song ->
                    Log.d("RepositoryTest", "  Song Translations: ${song.songTranslations}")
                    Log.d(
                        "RepositoryTest",
                        "  Audio: ${song.audioMedia[0].localAudioPath} ${song.audioMedia[1].localAudioPath}"
                    )
                    Log.d("RepositoryTest", "  Artist: ${song.artist})")
                    Log.d("RepositoryTest", "  Genre: ${song.genre}")
                    Log.d("RepositoryTest", "  Decade: ${song.era}")
                    Log.d("RepositoryTest", "-------------------------------------------------")
                }
            }

            is Result.Error -> {
                Log.d("RepositoryTest", "  ERROR: ${result2.error::class.simpleName}")
            }
        }

    }
}