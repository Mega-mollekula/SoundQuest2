package com.megamollekula.soundquest2.ui.intent

import com.megamollekula.soundquest2.domain.model.GameMode
import com.megamollekula.soundquest2.ui.model.UiMedia

sealed interface GameIntent {

    object Start : GameIntent

    data class ChooseAnswer(val content: UiMedia) : GameIntent

    object NextRound : GameIntent

    object RestartVerse : GameIntent

    data class SetMode(val gameMode: GameMode) : GameIntent

    object Reset : GameIntent

    object ScreenShown : GameIntent
}