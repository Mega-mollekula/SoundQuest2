package com.megamollekula.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "artist_translations",
    primaryKeys = ["artist_id", "language"],
    foreignKeys = [
        ForeignKey(
            entity = ArtistEntity::class,
            parentColumns = ["id"],
            childColumns = ["artist_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("artist_id")]
)
data class ArtistTranslationEntity(
    @ColumnInfo(name = "artist_id")
    val artistId: Long,
    val language: String,
    val name: String,
    val info: String
)