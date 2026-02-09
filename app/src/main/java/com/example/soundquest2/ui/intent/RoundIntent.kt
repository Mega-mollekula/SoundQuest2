package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.enums.HintType

sealed interface RoundIntent {

    data class AnswerSelected(
        val content: MediaContent
    ) : RoundIntent

    data object AgainClicked : RoundIntent

    data class HintClicked(
        val hintType: HintType
    ) : RoundIntent
}