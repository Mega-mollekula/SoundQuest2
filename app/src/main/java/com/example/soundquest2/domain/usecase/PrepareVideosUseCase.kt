package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.MediaPreloader

class PrepareVideosUseCase(
    private val preloader: MediaPreloader
) {
    fun execute(videoPaths: List<String>) {
        preloader.preloadVideos(videoPaths)
    }
}