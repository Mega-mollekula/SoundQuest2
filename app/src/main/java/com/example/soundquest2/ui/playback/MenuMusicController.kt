package com.example.soundquest2.ui.playback

import com.example.soundquest2.core.theme.AppTheme
import com.example.soundquest2.ui.playback.player.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuMusicController(
    private val scope: CoroutineScope,
    private val themeFlow: StateFlow<AppTheme>,
    private val player: MediaPlayer<String>
) {

    private var currentTheme: AppTheme? = null
    private var isPaused = false
    private var isPrepared = false

    init {
        scope.launch {
            themeFlow.collect { theme ->
                if (theme == currentTheme) return@collect

                currentTheme = theme
                isPaused = false
                isPrepared = true

                player.stop()

                val track = when (theme) {
                    AppTheme.LIGHT -> "music/light_theme.mp3"
                    AppTheme.DARK -> "music/dark_theme.mp3"
                }

                player.prepare(listOf(track))
                player.play()
            }
        }
    }

    fun pause() {
        if (!isPrepared) return
        isPaused = true
        player.stop()
    }

    fun resume() {
        if (!isPrepared || !isPaused) return
        isPaused = false
        player.play()
    }

    fun release() {
        player.release()
    }
}

