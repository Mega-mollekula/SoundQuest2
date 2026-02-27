package com.megamollekula.soundquest2.data.local.entity.song.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.megamollekula.soundquest2.data.local.entity.song.ArtistEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongVisualMediaEntity

data class SongWithDetails(
    @Embedded
    val song: SongEntity,

    @Relation(
        parentColumn = "artist_id",
        entityColumn = "id",
        entity = ArtistEntity::class
    )
    val artist: ArtistWithTranslations,

    @Relation(
        parentColumn = "id",
        entityColumn = "song_id"
    )
    val translations: List<SongTranslationEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "song_id"
    )
    val audioMedia: List<SongAudioMediaEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "song_id"
    )
    val visualMedia: SongVisualMediaEntity
)