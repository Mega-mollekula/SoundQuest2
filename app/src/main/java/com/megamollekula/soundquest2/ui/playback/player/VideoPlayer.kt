package com.megamollekula.soundquest2.ui.playback.player

import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer

interface VideoPlayer : MediaPlayer<Uri> {
    val exoPlayer: ExoPlayer
}