package com.megamollekula.soundquest2.data.remote.dto.films

import kotlinx.serialization.Serializable

@Serializable
data class FilmTranslationDto(
    val language: String,
    val description: String,
    val title: String
)