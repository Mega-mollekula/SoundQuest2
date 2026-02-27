package com.megamollekula.soundquest2.data.local.entity.film

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "film_media",
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = ["id"],
            childColumns = ["film_id"],
        )
    ],
    indices = [Index("film_id")]
)
data class FilmMediaEntity(
    @PrimaryKey
    @ColumnInfo("film_id")
    val filmId: Long,
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
