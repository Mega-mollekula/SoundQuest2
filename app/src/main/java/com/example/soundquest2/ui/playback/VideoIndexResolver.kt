package com.example.soundquest2.ui.playback

import com.example.soundquest2.domain.model.Round
import com.example.soundquest2.domain.model.content.MediaContent

class VideoIndexResolver(rounds: List<Round>) {

    private val indexByVideoPath: Map<String, Int>

    init {
        var index = 0
        indexByVideoPath = rounds
            .mapNotNull { round ->
                round.correct.getVideoPath()?.let { path ->
                    path to index++
                }
            }
            .toMap()
    }

    fun indexOf(content: MediaContent): Int? {
        val path = content.getVideoPath() ?: return null
        return indexByVideoPath[path]
    }
}
