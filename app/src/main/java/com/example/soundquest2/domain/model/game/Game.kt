package com.example.soundquest2.domain.model.game

import com.example.soundquest2.domain.model.GameGenre

data class Game(
    val developer: String,
    val publisher: String,
    val releaseYear: Int,
    val genre: GameGenre,
    val title: String,
    val gameTranslations: List<GameTranslation>,
    val gameMedia: GameMedia
)
