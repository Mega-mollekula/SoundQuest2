package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.GameResult
import com.megamollekula.soundquest2.domain.repository.GameResultRepository
import javax.inject.Inject

class GetResultsUseCase @Inject constructor (private val repository: GameResultRepository) {
    suspend operator fun invoke(): List<GameResult> {
        return repository.getResults()
    }
}