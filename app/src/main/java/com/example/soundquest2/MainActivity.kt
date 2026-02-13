package com.example.soundquest2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.soundquest2.core.language.AppLocaleController
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import kotlinx.coroutines.runBlocking
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.soundquest2.core.media.ExoAudioPlayer
import com.example.soundquest2.core.media.ExoVideoPlayer
import com.example.soundquest2.core.media.MenuExoPlayer
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.data.AppDatabase
import com.example.soundquest2.data.local.download.MediaDaos
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import com.example.soundquest2.data.local.storage.AndroidFileProvider
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.data.repository.FilmRepositoryImpl
import com.example.soundquest2.data.repository.GameRepositoryImpl
import com.example.soundquest2.data.repository.GameResultRepositoryImpl
import com.example.soundquest2.data.repository.SongRepositoryImpl
import com.example.soundquest2.domain.usecase.CheckAnswerUseCase
import com.example.soundquest2.domain.usecase.DownloadMediaUseCase
import com.example.soundquest2.domain.usecase.FinishGameUseCase
import com.example.soundquest2.domain.usecase.GenerateRoundsUseCase
import com.example.soundquest2.domain.usecase.InsertResultUseCase
import com.example.soundquest2.domain.usecase.LoadMediaUseCase
import com.example.soundquest2.domain.usecase.NextRoundUseCase
import com.example.soundquest2.domain.usecase.ResetGameUseCase
import com.example.soundquest2.domain.usecase.SetGameModeUseCase
import com.example.soundquest2.domain.usecase.StartGameUseCase
import com.example.soundquest2.ui.navigator.AppNavHost
import com.example.soundquest2.ui.playback.MenuMusicController
import com.example.soundquest2.ui.playback.VideoPreparer
import com.example.soundquest2.ui.theme.SoundQuestTheme
import com.example.soundquest2.ui.viewmodel.GameViewModel
import com.example.soundquest2.ui.viewmodel.MediaDownloadViewModel
import com.example.soundquest2.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

val Context.themeDataStore by preferencesDataStore(name = "theme_store")
val Context.languageDataStore: DataStore<Preferences> by preferencesDataStore(name = "language_store")

class MainActivity : ComponentActivity() {

    private lateinit var mediaDownloadViewModel: MediaDownloadViewModel
    private lateinit var gameViewModel: GameViewModel
    private lateinit var menuMusicController: MenuMusicController

    override fun attachBaseContext(newBase: Context) {
        val language = runBlocking {
            LanguageStorage(newBase.languageDataStore).getLanguage()
        }
        AppLocaleController(newBase).apply(language)
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val apiService = ApiService
        val context = applicationContext
        val db = AppDatabase.getDatabase(context)

        val gameResultRepository = GameResultRepositoryImpl(db.gameResultDao())
        val songRepository = SongRepositoryImpl(apiService, db)
        val gameRepository = GameRepositoryImpl(apiService, db)
        val filmRepository = FilmRepositoryImpl(apiService, db)
        val fileProvider = AndroidFileProvider(context)
        val daos = MediaDaos(
            filmMediaDao = db.filmMediaDao(),
            gameMediaDao = db.gameMediaDao(),
            songAudioMediaDao = db.songAudioMediaDao(),
            songVisualMediaDao = db.songVisualMediaDao()
        )
        val downloader = UnifiedMediaDownloader(
            apiService,
            daos = daos,
            fileProvider.getAudioBaseDir(),
            fileProvider.getVideoBaseDir()
        )

        val loadMediaUseCase = LoadMediaUseCase(
            songRepository, gameRepository, filmRepository
        )
        val downloadMediaUseCase = DownloadMediaUseCase(
            downloader
        )
        val setGameModeUseCase = SetGameModeUseCase()
        val generateRoundsUseCase = GenerateRoundsUseCase()
        val startGameUseCase = StartGameUseCase(
            loadMediaUseCase, generateRoundsUseCase
        )
        val checkAnswerUseCase = CheckAnswerUseCase()
        val nextRoundUseCase = NextRoundUseCase()
        val resetGameUseCase = ResetGameUseCase()
        val finishGameUseCase = FinishGameUseCase()
        val insertResultUseCase = InsertResultUseCase(gameResultRepository)
        val audioPlayer = ExoAudioPlayer(context, fileProvider.getAudioBaseDir())
        val videoPlayer = ExoVideoPlayer(context, fileProvider.getVideoBaseDir())
        val videoPreparer = VideoPreparer(videoPlayer)

        mediaDownloadViewModel = MediaDownloadViewModel(
            loadAudio = loadMediaUseCase, downloadMediaUseCase
        )

        val menuPlayer = MenuExoPlayer(this)

        lifecycleScope.launch {

            val theme = ThemeStorage(themeDataStore).getTheme()
            val language = LanguageStorage(languageDataStore).getLanguage()

            val settingsViewModel = SettingsViewModel(
                ThemeStorage(themeDataStore),
                LanguageStorage(languageDataStore),
                theme,
                language
            )

            menuMusicController = MenuMusicController(
                scope = lifecycleScope,
                themeFlow = settingsViewModel.theme,
                player = menuPlayer
            )

            gameViewModel = GameViewModel(
                setGameModeUseCase,
                startGameUseCase,
                checkAnswerUseCase,
                nextRoundUseCase,
                resetGameUseCase,
                finishGameUseCase,
                insertResultUseCase,
                languageFlow = settingsViewModel.language,
                audioPlayer,
                videoPlayer,
                videoPreparer
            )

            enableFullScreenMode()

            setContent {

                val theme by settingsViewModel.theme.collectAsState()
                val language by settingsViewModel.language.collectAsState()

                LaunchedEffect(language) {
                    AppLocaleController(this@MainActivity).apply(language)
                }

                SoundQuestTheme(
                    darkTheme = theme == AppTheme.DARK
                ) {
                    val navController = rememberNavController()
                    AppNavHost(
                        settingsViewModel = settingsViewModel,
                        navController = navController,
                        mediaDownloadViewModel = mediaDownloadViewModel,
                        gameViewModel = gameViewModel,
                        videoPlayer = videoPlayer,
                        menuMusicController = menuMusicController
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        menuMusicController.release()
    }
}

fun ComponentActivity.enableImmersiveMode() {
    WindowInsetsControllerCompat(window, window.decorView).apply {
        hide(
            WindowInsetsCompat.Type.statusBars() or
                    WindowInsetsCompat.Type.navigationBars()
        )
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun ComponentActivity.enableFullScreenMode() {
    enableEdgeToEdge()
    WindowCompat.setDecorFitsSystemWindows(window, false)
    enableImmersiveMode()
}