package com.example.soundquest2.ui.playback.player

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class MenuExoPlayer(
    context: Context
) : MenuPlayer {

    private val player = ExoPlayer.Builder(context)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(C.USAGE_GAME)
                .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                .build(),
            true
        )
        .build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }

    private var items: List<String> = emptyList()

    override fun prepare(items: List<String>) {
        this.items = items
    }

    override fun play(index: Int) {
        if (items.isEmpty()) return
        if (index !in items.indices) return

        val path = items[index]
        val mediaItem = MediaItem.fromUri("asset:///$path")

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun stop() {
        player.stop()
        player.clearMediaItems()
    }

    override fun pause() {
        player.pause()
    }

    override fun resume() {
        player.play()
    }

    override fun release() {
        player.release()
    }
}