package com.example.soundquest2.data.remote.dto.games

import kotlinx.serialization.Serializable

@Serializable
data class GameTranslationDto(
    val language: String,
    val title: String,
    val description: String
)