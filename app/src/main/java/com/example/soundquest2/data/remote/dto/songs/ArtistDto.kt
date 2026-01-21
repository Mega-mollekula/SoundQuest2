package com.example.soundquest2.data.remote.dto.songs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    val id: Long,
    @SerialName("picture_uri")
    val pictureUri: String,
    @SerialName("artist_translations")
    val artistTranslations: List<ArtistTranslationDto>
)