package com.example.soundquest2.domain.model.film

import com.example.soundquest2.domain.model.FilmType

data class Film(
    val director: String,
    val stars: String,
    val imdbRating: Double,
    val durationMinutes: Int,
    val releaseYear: Int,
    val filmType: FilmType,
    val filmTranslations: List<FilmTranslation>,
    val filmMedia: FilmMedia
)
