package com.example.soundquest2.core.media

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import java.io.File

class ExoVideoPlayer(
    context: Context,
    private val videoBaseDir: File
) : VideoPlayer {

    override val exoPlayer: ExoPlayer = ExoPlayer.Builder(context)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                .build(),
            false
        )
        .build().apply {
            volume = 0f
            repeatMode = Player.REPEAT_MODE_ONE
        }

    override fun prepare(items: List<String>) {
        exoPlayer.clearMediaItems()
        items.forEach {
            exoPlayer.addMediaItem(
                MediaItem.fromUri(resolve(it))
            )
        }
        exoPlayer.prepare()
    }

    override fun play(index: Int) {
        exoPlayer.seekTo(index, 0)
        exoPlayer.playWhenReady = true
    }

    override fun stop() {
        exoPlayer.stop()
    }

    override fun release() {
        exoPlayer.release()
    }

    private fun resolve(path: String): Uri {
        val file = File(path)
        return if (file.isAbsolute) {
            file.toUri()
        } else {
            File(videoBaseDir, path).toUri()
        }
    }
}

