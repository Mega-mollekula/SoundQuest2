package com.example.soundquest2.core.media

import android.net.Uri

interface AudioPlayer : MediaPlayer<Uri> {
    fun playSingle(uri: Uri)
    fun restart()
}