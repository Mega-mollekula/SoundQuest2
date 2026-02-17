package com.example.soundquest2.core.media

interface MediaPlayer<Uri> {
    fun prepare(items: List<Uri>)
    fun play(index: Int = 0)
    fun stop()
    fun release()
}