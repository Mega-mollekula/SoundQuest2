package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.enums.HintType
import com.example.soundquest2.ui.model.UiMedia

sealed interface RoundIntent {

    data class AnswerSelected(
        val content: UiMedia
    ) : RoundIntent

    data object AgainClicked : RoundIntent

    data class HintClicked(
        val hintType: HintType
    ) : RoundIntent
}