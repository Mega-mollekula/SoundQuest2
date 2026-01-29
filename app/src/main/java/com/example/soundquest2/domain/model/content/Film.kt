package com.example.soundquest2.domain.model.content

import com.example.soundquest2.domain.model.enums.ContentType
import com.example.soundquest2.domain.model.enums.FilmType
import com.example.soundquest2.domain.model.film.FilmMedia
import com.example.soundquest2.domain.model.film.FilmTranslation

data class Film(
    val director: String,
    val stars: String,
    val imdbRating: Double,
    val durationMinutes: Int,
    val releaseYear: Int,
    val filmType: FilmType,
    val filmTranslations: List<FilmTranslation>,
    val filmMedia: FilmMedia,
    override val contentType: ContentType = ContentType.FILM
) : MediaContent