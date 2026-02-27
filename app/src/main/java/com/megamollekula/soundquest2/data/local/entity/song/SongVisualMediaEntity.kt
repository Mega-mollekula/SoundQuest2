package com.megamollekula.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "song_visual_media",
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
data class SongVisualMediaEntity (
    @PrimaryKey
    @ColumnInfo(name = "song_id")
    val songId: Long,
    @ColumnInfo(name = "picture_uri")
    val pictureUri: String,
    @ColumnInfo(name = "video_path")
    val videoPath: String?,
    @ColumnInfo(name = "local_video_path")
    val localVideoPath: String? = null
)