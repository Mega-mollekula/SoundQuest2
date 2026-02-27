package com.megamollekula.soundquest2.domain.model.content

import com.megamollekula.soundquest2.domain.model.enums.ContentType
import com.megamollekula.soundquest2.domain.model.enums.FilmType
import com.megamollekula.soundquest2.domain.model.film.FilmMedia
import com.megamollekula.soundquest2.domain.model.film.FilmTranslation

data class Film(
    override val id: Long,
    val director: String,
    val stars: String,
    val imdbRating: Double,
    val durationMinutes: Int,
    val releaseYear: Int,
    val filmType: FilmType,
    val filmTranslations: List<FilmTranslation>,
    val filmMedia: FilmMedia,
    override val contentType: ContentType = ContentType.FILM
) : MediaContent {

    override fun getVideoPath(): String? {
        return filmMedia.localVideoPath
    }
}