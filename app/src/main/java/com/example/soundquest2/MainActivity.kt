package com.example.soundquest2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.soundquest2.ui.locale.AppLocaleController
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.soundquest2.ui.playback.player.ExoVideoPlayer
import com.example.soundquest2.ui.playback.player.MediaPlayer
import com.example.soundquest2.domain.model.enums.AppTheme
import com.example.soundquest2.data.local.datastore.languageDataStore
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.ui.navigator.AppNavHost
import com.example.soundquest2.ui.playback.controller.MenuMusicController
import com.example.soundquest2.ui.theme.SoundQuestTheme
import com.example.soundquest2.ui.viewmodel.GameViewModel
import com.example.soundquest2.ui.viewmodel.MediaDownloadViewModel
import com.example.soundquest2.ui.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()
    private val mediaDownloadViewModel: MediaDownloadViewModel by viewModels()
    private val gameViewModel: GameViewModel by viewModels()

    @Inject
    lateinit var menuPlayer: MediaPlayer<String>

    private lateinit var menuMusicController: MenuMusicController

    @Inject
    lateinit var videoPlayer: ExoVideoPlayer

    override fun attachBaseContext(newBase: Context) {
        val language = runBlocking {
            LanguageStorage(newBase.languageDataStore).getLanguage()
        }
        AppLocaleController(newBase).apply(language)
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        menuMusicController = MenuMusicController(
            scope = lifecycleScope,
            themeFlow = settingsViewModel.theme,
            player = menuPlayer
        )

        lifecycleScope.launch {

            enableFullScreenMode()

            setContent {

                val theme by settingsViewModel.theme.collectAsState()
                val language by settingsViewModel.language.collectAsState()

                key(language) {

                    AppLocaleController(this@MainActivity).apply(language)

                    SoundQuestTheme(darkTheme = theme == AppTheme.DARK) {
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