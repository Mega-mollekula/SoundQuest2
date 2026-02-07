package com.example.soundquest2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
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
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

val Context.themeDataStore by preferencesDataStore(name = "theme_store")
val Context.languageDataStore: DataStore<Preferences> by preferencesDataStore(name = "language_store")

class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        val language = runBlocking {
            LanguageStorage(newBase.languageDataStore).getLanguage()
        }
        AppLocaleController(newBase).apply(language)
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

            val theme = ThemeStorage(themeDataStore).getTheme()
            val language = LanguageStorage(languageDataStore).getLanguage()

            val settingsViewModel = SettingsViewModel(
                ThemeStorage(themeDataStore),
                LanguageStorage(languageDataStore),
                theme,
                language
            )

            enableFullScreenMode()

            setContent {

//                val theme by settingsViewModel.theme.collectAsState()
//                val language by settingsViewModel.language.collectAsState()
//
//                LaunchedEffect(language) {
//                    AppLocaleController(this@MainActivity).apply(language)
//                }

//                SoundQuestThemeProvider(properties) {
//                    //AppNavGraph()
//                }
            }
        }
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