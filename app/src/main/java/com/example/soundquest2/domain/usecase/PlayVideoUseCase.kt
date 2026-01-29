package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.VideoPlayer

class PlayVideoUseCase(
    private val videoPlayer: VideoPlayer
) {
    fun execute(index: Int) {
        videoPlayer.play(index)
    }
}