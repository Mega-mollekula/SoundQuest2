package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.Round
import com.example.soundquest2.domain.model.content.MediaContent

class CheckAnswerUseCase {
    operator fun invoke(selected: MediaContent, round: Round): Boolean {
        return selected == round.correct
    }
}