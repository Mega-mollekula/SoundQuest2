package com.example.soundquest2.core.media

class MediaPreloader(
    private val videoPlayer: VideoPlayer
) {
    fun preloadVideos(paths: List<String>) {
        videoPlayer.prepare(paths)
    }
}
