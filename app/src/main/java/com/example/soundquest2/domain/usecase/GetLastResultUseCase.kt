package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.repository.GameResultRepository
import javax.inject.Inject

class GetLastResultUseCase @Inject constructor (private val repository: GameResultRepository) {
    suspend operator fun invoke() {
        repository.getLastResult()
    }
}