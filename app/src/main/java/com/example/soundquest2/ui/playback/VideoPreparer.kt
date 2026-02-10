package com.example.soundquest2.ui.playback

import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.domain.model.Round

class VideoPreparer(
    private val videoPlayer: VideoPlayer
) {

    fun prepare(rounds: List<Round>): VideoIndexResolver {
        val paths = rounds.mapNotNull { it.correct.getVideoPath() }
        videoPlayer.prepare(paths)
        return VideoIndexResolver(rounds)
    }
}
