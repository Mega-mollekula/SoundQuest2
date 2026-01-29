package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.Round
import com.example.soundquest2.domain.model.content.MediaContent

class GenerateRoundsUseCase {

    operator fun invoke(
        items: List<MediaContent>,
        roundsCount: Int = 5,
        optionsPerRound: Int = 4
    ): List<Round> {

        if (items.isEmpty()) return emptyList()

        val allItems = items.toMutableList()
        val rounds = mutableListOf<Round>()

        val unused = allItems.shuffled().toMutableList()
        val usedAcrossRounds = mutableSetOf<MediaContent>()
        val usedAsCorrect = mutableSetOf<MediaContent>()

        repeat(roundsCount) {

            val correct = when {
                unused.isNotEmpty() -> unused.removeAt(0)
                else -> allItems.firstOrNull { it !in usedAsCorrect } ?: allItems.random()
            }

            usedAsCorrect.add(correct)

            val options = mutableListOf<MediaContent>()

            while (options.size < optionsPerRound - 1) {

                val candidate = allItems.firstOrNull {
                    it != correct && it !in options && it !in usedAcrossRounds && it.contentType == correct.contentType
                } ?: allItems.firstOrNull {
                    it != correct && it !in options && it.contentType == correct.contentType
                }

                if (candidate == null) break
                options.add(candidate)
            }

            val roundOptions = (options + correct).shuffled()
            usedAcrossRounds.addAll(roundOptions)

            rounds.add(
                Round(
                    correct = correct,
                    options = roundOptions
                )
            )
        }
        return rounds.shuffled()
    }
}