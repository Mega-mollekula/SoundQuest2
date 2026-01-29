package com.example.soundquest2.domain.model.content

import com.example.soundquest2.domain.model.ContentType
import com.example.soundquest2.domain.model.GameGenre
import com.example.soundquest2.domain.model.game.GameMedia
import com.example.soundquest2.domain.model.game.GameTranslation

data class Game(
    val developer: String,
    val publisher: String,
    val releaseYear: Int,
    val genre: GameGenre,
    val title: String,
    val gameTranslations: List<GameTranslation>,
    val gameMedia: GameMedia,
    override val contentType: ContentType = ContentType.GAME
) : MediaContent