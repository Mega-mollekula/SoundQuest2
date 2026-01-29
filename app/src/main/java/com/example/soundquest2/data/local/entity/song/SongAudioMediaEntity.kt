package com.example.soundquest2.data.local.entity.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.soundquest2.domain.model.enums.SegmentType

@Entity(
    tableName = "song_audio_media",
    indices = [
        Index(
            value = ["song_id", "segment"],
            unique = true
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = SongEntity::class,
            parentColumns = ["id"],
            childColumns = ["song_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SongAudioMediaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "song_id")
    val songId: Long,
    val segment: SegmentType,
    val duration: Int,
    @ColumnInfo(name = "audio_path")
    val audioPath: String,
    @ColumnInfo(name = "local_audio_path")
    val localAudioPath: String? = null
)