package com.example.soundquest2.data.remote.dto.songs

import kotlinx.serialization.Serializable

@Serializable
data class SongTranslationDto(
    val language: String,
    val info: String
)
