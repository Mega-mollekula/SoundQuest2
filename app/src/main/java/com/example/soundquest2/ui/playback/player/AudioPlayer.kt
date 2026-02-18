package com.example.soundquest2.ui.playback.player

import android.net.Uri

interface AudioPlayer {
    fun play(uri: Uri)
    fun restart()
    fun stop()
    fun pause()
    fun resume()
    fun release()
}