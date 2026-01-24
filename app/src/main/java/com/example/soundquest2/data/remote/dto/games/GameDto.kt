package com.example.soundquest2.data.remote.dto.games

import com.example.soundquest2.domain.model.GameGenre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id: Long,
    val developer: String,
    val publisher: String,
    @SerialName("release_year")
    val releaseYear: Int,
    val genre: GameGenre,
    val title: String,

    @SerialName("game_translations")
    val gameTranslations: List<GameTranslationDto>,
    @SerialName("game_media")
    val gameMedia: GameMediaDto
)