package com.megamollekula.soundquest2.data.remote.dto.songs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongVisualMediaDto(
    @SerialName("picture_uri")
    val pictureUri: String,
    @SerialName("video_path")
    val videoPath: String?
)
