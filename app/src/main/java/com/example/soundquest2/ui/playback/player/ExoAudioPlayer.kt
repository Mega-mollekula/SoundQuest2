package com.example.soundquest2.ui.playback.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class ExoAudioPlayer(
    context: Context,
) : AudioPlayer {

    private val player = ExoPlayer.Builder(context)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(C.USAGE_GAME)
                .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                .build(),
            true
        )
        .build()

    private var currentPath: Uri? = null

    override fun playSingle(uri: Uri) {
        currentPath = uri
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
    }

    override fun restart() {
        if (currentPath == null) return
        player.seekTo(0)
        player.play()
    }

    override fun prepare(items: List<Uri>) {
        // не используется для аудио
    }

    override fun play(index: Int) = Unit

    override fun stop() {
        player.stop()
    }

    override fun release() {
        player.release()
    }
}
