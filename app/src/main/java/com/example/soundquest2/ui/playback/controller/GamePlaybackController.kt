package com.example.soundquest2.ui.playback.controller

import android.net.Uri
import com.example.soundquest2.domain.model.Round
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.SegmentType
import com.example.soundquest2.ui.playback.VideoIndexResolver
import com.example.soundquest2.ui.playback.player.AudioPlayer
import com.example.soundquest2.ui.playback.player.VideoPlayer

class GamePlaybackController(
    private val audioPlayer: AudioPlayer,
    private val videoPlayer: VideoPlayer,
    private val videoIndexResolver: VideoIndexResolver
) {

    // audio

    fun playRoundAudio(round: Round) {
        when (round.correct) {
            is Song -> {
                val audio = round.correct.getAudioByType(SegmentType.VERSE)
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
            is Game -> {
                val audio = round.correct.gameMedia
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
            is Film -> {
                val audio = round.correct.filmMedia
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
        }
    }

    fun playResultAudio(round: Round) {
        when (round.correct) {
            is Song -> {
                val audio = round.correct.getAudioByType(SegmentType.CHORUS)
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
            is Game -> {
                val audio = round.correct.gameMedia
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
            is Film -> {
                val audio = round.correct.filmMedia
                audioPlayer.playSingle(Uri.parse(audio.localAudioPath!!))
            }
        }
    }

    fun restartAudio() = audioPlayer.restart()

    fun stopAudio() = audioPlayer.stop()

    // video

    fun playVideoForRound(round: Round) {
        val index = videoIndexResolver.indexOf(round.correct) ?: return
        videoPlayer.exoPlayer.seekTo(index, 0)
        videoPlayer.exoPlayer.playWhenReady = true
    }

    fun release() {
        audioPlayer.release()
        videoPlayer.release()
    }
}
