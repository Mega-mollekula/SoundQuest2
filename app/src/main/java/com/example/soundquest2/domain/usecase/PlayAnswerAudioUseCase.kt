package com.example.soundquest2.domain.usecase

import com.example.soundquest2.core.media.AudioPlayer
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.SegmentType

class PlayAnswerAudioUseCase(
    private val audioPlayer: AudioPlayer
) {

    operator fun invoke(media: MediaContent) {
        when (media) {
            is Song -> {
                val audio = media.getAudioByType(SegmentType.CHORUS)
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
            is Film -> {
                val audio = media.filmMedia.localAudioPath
                audioPlayer.playSingle(audio!!)
            }
            is Game -> {
                val audio = media.gameMedia.localAudioPath
                audioPlayer.playSingle(audio!!)
            }
        }
    }
}