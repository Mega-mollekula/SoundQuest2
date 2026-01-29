package com.example.soundquest2.data.local.entity.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soundquest2.domain.model.enums.GameGenre

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: Long,
    val developer: String,
    val publisher: String,
    @ColumnInfo("release_year")
    val releaseYear: Int,
    val genre: GameGenre,
    val title: String,
)
