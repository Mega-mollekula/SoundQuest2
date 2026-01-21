package com.example.soundquest2.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SongTranslationDto(
    val language: String,
    val title: String,
    val info: String
)
