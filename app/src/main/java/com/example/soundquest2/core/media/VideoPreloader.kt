package com.example.soundquest2.core.media

class VideoPreloader(
    private val videoPlayer: VideoPlayer
) {
    fun preloadVideos(paths: List<String>) {
        videoPlayer.prepare(paths)
    }
}
