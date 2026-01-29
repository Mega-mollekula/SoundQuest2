package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.AudioPlayer

class RepeatAudioUseCase(
    private val audioPlayer: AudioPlayer
) {
    fun execute() = audioPlayer.restart()
}