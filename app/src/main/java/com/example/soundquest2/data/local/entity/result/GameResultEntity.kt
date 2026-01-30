package com.example.soundquest2.data.local.entity.result

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class GameResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "rounds_count")
    val roundsCount: Int,
    @ColumnInfo(name = "guessed_songs_count")
    val guessedSongsCount: Int,
    val gameMode: GameModeType
)