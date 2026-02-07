package com.example.soundquest2.ui.model

import com.example.soundquest2.domain.model.enums.ContentType
import com.example.soundquest2.domain.model.enums.GameGenre

data class UiGame(
    val developer: String,
    val publisher: String,
    val releaseYear: Int,
    val genre: GameGenre,
    override val title: String,
    val description: String,
    override val mediaType: ContentType = ContentType.GAME
) : UiMedia