package com.megamollekula.soundquest2.ui.playback

import android.net.Uri
import com.megamollekula.soundquest2.domain.model.Round
import com.megamollekula.soundquest2.ui.playback.player.VideoPlayer
import javax.inject.Inject

class VideoPreparer @Inject constructor(
    private val videoPlayer: VideoPlayer
) {

    fun prepare(rounds: List<Round>): VideoIndexResolver {
        val paths = rounds.mapNotNull { it.correct.getVideoPath() }.map { Uri.parse(it) }
        videoPlayer.prepare(paths)
        return VideoIndexResolver(rounds)
    }
}
