package com.example.soundquest2.ui.playback.player

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

    override val player: ExoPlayer = ExoPlayer.Builder(context)
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
        player.clearMediaItems()
        items.forEach { uri ->
            player.addMediaItem(
                MediaItem.fromUri(uri)
            )
        }
        player.prepare()
    }

    override fun play(index: Int) {
        player.seekTo(index, 0)
        player.playWhenReady = true
    }

    override fun stop() {
        player.stop()
    }

    override fun release() {
        player.release()
    }
}

