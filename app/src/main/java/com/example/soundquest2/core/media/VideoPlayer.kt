package com.example.soundquest2.core.media

import androidx.media3.exoplayer.ExoPlayer

interface VideoPlayer : MediaPlayer<String> {
    val exoPlayer: ExoPlayer
}