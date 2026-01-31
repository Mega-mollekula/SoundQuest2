package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.repository.GameResultRepository

class GetLastResultUseCase (private val repository: GameResultRepository) {
    suspend operator fun invoke() {
        repository.getLastResult()
    }
}