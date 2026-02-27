package com.megamollekula.soundquest2.domain.model.content

import com.megamollekula.soundquest2.domain.model.enums.ContentType
import com.megamollekula.soundquest2.domain.model.enums.GameGenre
import com.megamollekula.soundquest2.domain.model.game.GameMedia
import com.megamollekula.soundquest2.domain.model.game.GameTranslation

data class Game(
    override val id: Long,
    val developer: String,
    val publisher: String,
    val releaseYear: Int,
    val genre: GameGenre,
    val title: String,
    val gameTranslations: List<GameTranslation>,
    val gameMedia: GameMedia,
    override val contentType: ContentType = ContentType.GAME
) : MediaContent {

    override fun getVideoPath(): String? {
        return gameMedia.localVideoPath
    }
}