package com.example.soundquest2.core.media

import android.content.Context
import android.net.Uri
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class ExoVideoPlayer(
    context: Context,
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

    override fun prepare(items: List<Uri>) {
        exoPlayer.clearMediaItems()
        items.forEach { uri ->
            exoPlayer.addMediaItem(
                MediaItem.fromUri(uri)
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
}

