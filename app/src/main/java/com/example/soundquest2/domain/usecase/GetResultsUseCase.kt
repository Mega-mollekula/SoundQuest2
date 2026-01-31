package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.repository.GameResultRepository

class GetResultsUseCase(private val repository: GameResultRepository) {
    suspend operator fun invoke(): List<GameResult> {
        return repository.getResults()
    }
}