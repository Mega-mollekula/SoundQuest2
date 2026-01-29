package com.example.soundquest2.core.media

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import java.io.File

class ExoAudioPlayer(
    context: Context,
    private val audioBaseDir: File
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

    private var currentPath: String? = null

    override fun playSingle(path: String) {
        currentPath = path
        player.setMediaItem(MediaItem.fromUri(resolve(path)))
        player.prepare()
        player.play()
    }

    override fun restart() {
        if (currentPath == null) return
        player.seekTo(0)
        player.play()
    }

    override fun prepare(items: List<String>) {
        // не используется для аудио
    }

    override fun play(index: Int) = Unit

    override fun stop() {
        player.stop()
    }

    override fun release() {
        player.release()
    }

    private fun resolve(path: String): Uri {
        val file = File(path)
        return if (file.isAbsolute) {
            file.toUri()
        } else {
            File(audioBaseDir, path).toUri()
        }
    }
}
