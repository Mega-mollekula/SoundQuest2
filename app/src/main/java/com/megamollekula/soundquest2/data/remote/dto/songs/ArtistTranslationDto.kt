package com.megamollekula.soundquest2.data.remote.dto.songs

import kotlinx.serialization.Serializable

@Serializable
data class ArtistTranslationDto(
    val language: String,
    val name: String,
    val info: String
)