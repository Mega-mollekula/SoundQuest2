package com.example.soundquest2.data.remote.dto.films

import com.example.soundquest2.domain.model.FilmType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    val id: Long,
    val director: String,
    val stars: String,
    @SerialName("imdb_rating")
    val imdbRating: Double,
    @SerialName("duration_minutes")
    val durationMinutes: Int,
    @SerialName("release_year")
    val releaseYear: Int,
    @SerialName("film_type")
    val filmType: FilmType,

    @SerialName("film_translations")
    val filmTranslations: List<FilmTranslationDto>,
    @SerialName("film_media")
    val filmMedia: FilmMediaDto
)