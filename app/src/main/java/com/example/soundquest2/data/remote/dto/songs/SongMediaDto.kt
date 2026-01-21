package com.example.soundquest2.data.remote.dto.songs

import com.example.soundquest2.domain.model.SegmentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongMediaDto(
    val segment: SegmentType,
    val duration: Int,
    @SerialName("audio_path")
    val audioPath: String,
    @SerialName("video_path")
    val videoPath: String
)
