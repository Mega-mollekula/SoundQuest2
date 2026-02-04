package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.content.MediaContent

sealed interface GameIntent {

    object Start : GameIntent

    data class ChooseAnswer(val content: MediaContent) : GameIntent

    object NextRound : GameIntent

    object RestartVerse : GameIntent

    data class SetMode(val gameMode: GameMode) : GameIntent

    object Reset : GameIntent
}