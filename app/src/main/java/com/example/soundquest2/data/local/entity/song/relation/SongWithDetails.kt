package com.example.soundquest2.data.local.entity.song.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.relation.ArtistWithTranslations
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.SongMediaEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity

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
    val media: List<SongMediaEntity>
)