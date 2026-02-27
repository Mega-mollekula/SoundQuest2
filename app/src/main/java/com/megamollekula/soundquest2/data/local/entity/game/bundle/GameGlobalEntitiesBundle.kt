package com.megamollekula.soundquest2.data.local.entity.game.bundle

import com.megamollekula.soundquest2.data.local.entity.game.GameEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameMediaEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameTranslationEntity

data class GameGlobalEntitiesBundle(
    val games: List<GameEntity>,
    val translations: List<GameTranslationEntity>,
    val media: List<GameMediaEntity>
)
