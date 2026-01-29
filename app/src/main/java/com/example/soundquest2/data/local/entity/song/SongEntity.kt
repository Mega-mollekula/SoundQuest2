package com.example.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre

@Entity(
    tableName = "songs",
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
data class SongEntity(
    @PrimaryKey
    val id: Long,
    val genre: Genre,
    val era: Era,
    val title: String,
    @ColumnInfo(name = "artist_id")
    val artistId: Long
)