package com.example.soundquest2.data.remote.dto.films

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmMediaDto(
    val duration: Int,
    @SerialName("audio_path")
    val audioPath: String,
    @SerialName("video_path")
    val videoPath: String,
    @SerialName("picture_uri")
    val pictureUri: String,
)