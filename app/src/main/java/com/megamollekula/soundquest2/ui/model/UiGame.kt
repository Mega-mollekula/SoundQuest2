package com.megamollekula.soundquest2.ui.model

import com.megamollekula.soundquest2.domain.model.enums.ContentType
import com.megamollekula.soundquest2.domain.model.enums.GameGenre

data class UiGame(
    override val id: Long,
    val developer: String,
    val publisher: String,
    val releaseYear: Int,
    val genre: GameGenre,
    override val pictureUri: String,
    override val title: String,
    val description: String,
    override val mediaType: ContentType = ContentType.GAME,
    override val localVideoPath: String? = null
) : UiMedia