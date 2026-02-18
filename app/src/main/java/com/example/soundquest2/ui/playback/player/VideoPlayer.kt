package com.example.soundquest2.ui.playback.player

import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer

interface VideoPlayer {
    val player: ExoPlayer
    fun prepare(items: List<Uri>)
    fun play(index: Int)
    fun stop()
    fun release()
}