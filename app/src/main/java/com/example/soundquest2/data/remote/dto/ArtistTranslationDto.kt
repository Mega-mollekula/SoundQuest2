package com.example.soundquest2.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArtistTranslationDto(
    val language: String,
    val name: String,
    val info: String
)