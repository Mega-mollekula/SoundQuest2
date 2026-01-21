package com.example.soundquest2.data.remote.dto.films

import kotlinx.serialization.Serializable

@Serializable
data class FilmTranslationDto(
    val language: String,
    val title: String,
    val description: String
)