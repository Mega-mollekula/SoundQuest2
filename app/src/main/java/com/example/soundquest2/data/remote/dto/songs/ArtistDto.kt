package com.example.soundquest2.data.remote.dto.songs

import com.example.soundquest2.domain.model.enums.Gender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    val id: Long,
    @SerialName("country_code")
    val countryCode: String,
    val gender: Gender,
    @SerialName("picture_uri")
    val pictureUri: String,
    @SerialName("artist_translations")
    val artistTranslations: List<ArtistTranslationDto>
)