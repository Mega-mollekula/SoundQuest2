package com.example.soundquest2.ui.playback

import com.example.soundquest2.core.media.AudioPlayer
import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.domain.model.Round
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.SegmentType

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
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
            is Game -> {
                val audio = round.correct.gameMedia
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
            is Film -> {
                val audio = round.correct.filmMedia
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
        }
    }

    fun playResultAudio(round: Round) {
        when (round.correct) {
            is Song -> {
                val audio = round.correct.getAudioByType(SegmentType.CHORUS)
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
            is Game -> {
                val audio = round.correct.gameMedia
                audioPlayer.playSingle(audio.localAudioPath!!)
            }
            is Film -> {
                val audio = round.correct.filmMedia
                audioPlayer.playSingle(audio.localAudioPath!!)
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

    fun stopVideo() = videoPlayer.exoPlayer.stop()

    fun release() {
        audioPlayer.release()
        videoPlayer.release()
    }
}
