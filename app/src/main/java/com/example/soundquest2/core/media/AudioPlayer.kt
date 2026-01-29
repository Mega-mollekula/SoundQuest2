package com.example.soundquest2.core.media

interface AudioPlayer : MediaPlayer<String> {
    fun playSingle(path: String)
    fun restart()
}