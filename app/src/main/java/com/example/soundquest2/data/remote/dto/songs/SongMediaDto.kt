package com.example.soundquest2.data.remote.dto.songs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongMediaDto(
    val segment: String,
    val duration: Int,
    @SerialName("audio_path")
    val audioPath: String,
    @SerialName("video_path")
    val videoPath: String
)
