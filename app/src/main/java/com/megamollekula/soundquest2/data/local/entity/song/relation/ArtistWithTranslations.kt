package com.megamollekula.soundquest2.data.local.entity.song.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.megamollekula.soundquest2.data.local.entity.song.ArtistEntity
import com.megamollekula.soundquest2.data.local.entity.song.ArtistTranslationEntity

data class ArtistWithTranslations(
    @Embedded
    val artist: ArtistEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "artist_id"
    )
    val translations: List<ArtistTranslationEntity>
)