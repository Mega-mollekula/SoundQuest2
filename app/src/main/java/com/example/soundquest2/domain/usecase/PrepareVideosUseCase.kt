package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.VideoPreloader

class PrepareVideosUseCase(
    private val preloader: VideoPreloader
) {
    fun execute(videoPaths: List<String>) {
        preloader.preloadVideos(videoPaths)
    }
}