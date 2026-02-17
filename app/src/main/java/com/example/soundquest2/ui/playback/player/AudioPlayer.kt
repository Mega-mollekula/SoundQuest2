package com.example.soundquest2.ui.playback.player

import android.net.Uri

interface AudioPlayer : MediaPlayer<Uri> {
    fun playSingle(uri: Uri)
    fun restart()
}