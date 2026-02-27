package com.megamollekula.soundquest2.data.local.entity.game.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.megamollekula.soundquest2.data.local.entity.game.GameEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameMediaEntity
import com.megamollekula.soundquest2.data.local.entity.game.GameTranslationEntity

data class GameWithDetails(
    @Embedded
    val game: GameEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "game_id",
    )
    val translations: List<GameTranslationEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "game_id"
    )
    val gameMedia: GameMediaEntity
)