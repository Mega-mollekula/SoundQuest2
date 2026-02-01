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
import androidx.lifecycle.lifecycleScope
import com.example.soundquest2.core.language.AppLocaleController
import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.core.theme.ThemeController
import com.example.soundquest2.core.theme.ThemeResolver
import com.example.soundquest2.data.local.storage.LanguageStorage
import com.example.soundquest2.data.local.storage.ThemeStorage
import com.example.soundquest2.ui.theme.SoundQuestThemeProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

val Context.themeDataStore by preferencesDataStore(name = "theme_store")
val Context.languageDataStore: DataStore<Preferences> by preferencesDataStore(name = "language_store")

class MainActivity : ComponentActivity() {

    private lateinit var controller: ThemeController
    private lateinit var languageStorage: LanguageStorage

    override fun attachBaseContext(newBase: Context) {
        val languageDataStore = newBase.languageDataStore
        val storage = LanguageStorage(languageDataStore)

        val language = runBlocking {
            storage.getLanguage()
        }

        val localeController = AppLocaleController(newBase)
        localeController.applyLanguage(language)

        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = ThemeStorage(themeDataStore)
        controller = ThemeController(storage)
        val resolver = ThemeResolver()
        languageStorage = LanguageStorage(languageDataStore)

        val theme = runBlocking {
            controller.getCurrentTheme()
        }

        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableImmersiveMode()

        setContent {
            val properties = remember(theme) {
                resolver.resolve(theme)
            }
            SoundQuestThemeProvider(properties) {
                //AppNavGraph()
            }
        }
    }

    private fun changeTheme(theme: AppTheme) {
        lifecycleScope.launch {
            controller.changeTheme(theme)
            recreate()
        }
    }

    private fun changeLanguage(languageCode: String) {
        lifecycleScope.launch {
            languageStorage.setLanguage(languageCode)
            recreate()
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