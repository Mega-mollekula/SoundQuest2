package com.example.soundquest2.ui.navigator

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundquest2.ui.playback.player.VideoPlayer
import com.example.soundquest2.ui.playback.controller.MenuMusicController
import com.example.soundquest2.ui.screen.game.GameScreen
import com.example.soundquest2.ui.screen.main.LanguageSelectionScreen
import com.example.soundquest2.ui.screen.main.MainScreen
import com.example.soundquest2.ui.screen.main.MediaDownloadScreen
import com.example.soundquest2.ui.screen.main.SettingsScreen
import com.example.soundquest2.ui.screen.main.SongSelectionScreen
import com.example.soundquest2.ui.screen.main.SoundtracksSelectionScreen
import com.example.soundquest2.ui.screen.main.ThemeSelectionScreen
import com.example.soundquest2.ui.viewmodel.GameViewModel
import com.example.soundquest2.ui.viewmodel.MediaDownloadViewModel
import com.example.soundquest2.ui.viewmodel.SettingsViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    mediaDownloadViewModel: MediaDownloadViewModel,
    settingsViewModel: SettingsViewModel,
    gameViewModel: GameViewModel,
//    statisticViewModel: StatisticViewModel,
    videoPlayer: VideoPlayer,
    menuMusicController: MenuMusicController
) {
    val safeNav = remember { SafeNavController(navController) }

    val currentGameMode by gameViewModel.gameMode.collectAsState()
    val gameUiState by gameViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {

        composable("main") {
            menuMusicController.resume()
            MainScreen(
                onGameIntent = { intent -> gameViewModel.onIntent(intent) },
                toSoundtrackScreen = { safeNav.navigate("soundtracks") },
                toSongScreen = { safeNav.navigate("songs") },
                toAchievementsScreen = { safeNav.navigate("achievements") },
                toSettingsScreen = {
                    safeNav.navigate("settings")
                },
                toMarketScreen = { safeNav.navigate("market") },
                toDownloadScreen = {
                    safeNav.navigate("download")
                }
            )
        }

        composable("settings") {
            SettingsScreen(
                toLanguageScreen = { safeNav.navigate("language") },
                toThemeScreen = { safeNav.navigate("theme") },
                onExit = { safeNav.safePop() }
            )
        }

        composable("language") {
            LanguageSelectionScreen(
                onIntent = { intent -> settingsViewModel.onIntent(intent) },
                onExit = { safeNav.navigate("main") }
            )
        }

        composable("theme") {
            ThemeSelectionScreen(
                onIntent = { intent -> settingsViewModel.onIntent(intent) },
                onExit = { safeNav.safePop() }
            )
        }

        composable("download") {
            BackHandler(enabled = true) {}
            MediaDownloadScreen(
                uiState = mediaDownloadViewModel.uiState.collectAsState().value,
                toGameScreen = { safeNav.navigate("game") },
                count = 10,
                gameMode = currentGameMode,
                onDownloadIntent = { intent -> mediaDownloadViewModel.onIntent(intent) },
                onExit = { safeNav.safePop() }
            )
        }

        composable("soundtracks") {
            SoundtracksSelectionScreen(
                onExit = { safeNav.safePop() },
                onIntent = { intent -> gameViewModel.onIntent(intent) },
                toDownloadScreen = { safeNav.navigate("download") }
            )
        }

        composable("songs") {
            SongSelectionScreen(
                onExit = { safeNav.safePop() },
                onIntent = { intent -> gameViewModel.onIntent(intent) },
                toDownloadScreen = { safeNav.navigate("download") }
            )
        }

        composable("game") {
            menuMusicController.pause()
            BackHandler(enabled = true) {}
            GameScreen(
                state = gameUiState,
                videoPlayer = videoPlayer,
                onIntent = { intent -> gameViewModel.onIntent(intent) },
                onExitGame = { safeNav.navigate("main") }
            )
        }
    }
}