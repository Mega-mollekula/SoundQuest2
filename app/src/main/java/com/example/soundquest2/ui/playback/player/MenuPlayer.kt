package com.example.soundquest2.ui.playback.player

interface MenuPlayer {
    fun prepare(items: List<String>)
    fun play(index: Int = 0)
    fun stop()
    fun release()
    fun pause()
    fun resume()
}