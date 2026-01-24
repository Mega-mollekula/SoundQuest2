package com.example.soundquest2.data.remote.dto.games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameMediaDto(
    val duration: Int,
    @SerialName("audio_path")
    val audioPath: String,
    @SerialName("video_path")
    val videoPath: String,
    @SerialName("picture_uri")
    val pictureUri: String
)