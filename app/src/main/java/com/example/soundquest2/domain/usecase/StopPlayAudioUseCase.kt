package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.AudioPlayer

class StopPlayAudioUseCase (
    private val player: AudioPlayer
){
    operator fun invoke() {
        player.stop()
    }
}