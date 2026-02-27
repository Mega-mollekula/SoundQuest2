package com.megamollekula.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "song_translations",
    primaryKeys = ["song_id", "language"],
    foreignKeys = [
        ForeignKey(
            entity = SongEntity::class,
            parentColumns = ["id"],
            childColumns = ["song_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("song_id")]
)
data class SongTranslationEntity(
    @ColumnInfo(name = "song_id")
    val songId: Long,
    val language: String,
    val info: String
)