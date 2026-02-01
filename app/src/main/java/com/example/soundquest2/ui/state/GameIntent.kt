package com.example.soundquest2.ui.state

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.content.MediaContent

sealed interface GameIntent {
    object Start : GameIntent

    data class ChooseAnswer(val audio: MediaContent) : GameIntent

    object NextRound : GameIntent

    object RestartAudio : GameIntent

    data class SetMode(val gameMode: GameMode) : GameIntent
}