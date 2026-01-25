package com.example.soundquest2.data.local.entity.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "game_translations",
    primaryKeys = ["game_id", "language"],
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["game_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("game_id")]
)
data class GameTranslationEntity(
    @ColumnInfo("game_id")
    val gameId: Long,
    val language: String,
    val description: String
)
