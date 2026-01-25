package com.example.soundquest2.data.local.entity.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "game_media",
    indices = [Index("game_id")],
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["game_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GameMediaEntity(
    @PrimaryKey
    @ColumnInfo("game_id")
    val gameId: Long,
    val duration: Int,
    @ColumnInfo("audio_path")
    val audioPath: String,
    @ColumnInfo("video_path")
    val videoPath: String,
    @ColumnInfo("picture_uri")
    val pictureUri: String,
    @ColumnInfo("local_audio_path")
    val localAudioPath: String? = null,
    @ColumnInfo("local_video_path")
    val localVideoPath: String? = null
)
