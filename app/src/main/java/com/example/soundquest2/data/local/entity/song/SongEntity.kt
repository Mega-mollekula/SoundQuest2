package com.example.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soundquest2.domain.model.Era
import com.example.soundquest2.domain.model.Genre

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: Long,
    val genre: Genre,
    val era: Era,
    val title: String,
    @ColumnInfo(name = "artist_id")
    val artistId: Long
)