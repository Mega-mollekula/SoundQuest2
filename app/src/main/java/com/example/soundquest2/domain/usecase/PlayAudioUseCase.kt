package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.AudioPlayer

class PlayAudioUseCase(
    private val audioPlayer: AudioPlayer
) {
    fun execute(path: String) {
        audioPlayer.playSingle(path)
    }
}