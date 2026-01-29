package com.example.soundquest2.core.media

interface MediaPlayer<T> {
    fun prepare(items: List<T>)
    fun play(index: Int = 0)
    fun stop()
    fun release()
}