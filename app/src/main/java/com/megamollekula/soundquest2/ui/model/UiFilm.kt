package com.megamollekula.soundquest2.ui.model

import com.megamollekula.soundquest2.domain.model.enums.ContentType
import com.megamollekula.soundquest2.domain.model.enums.FilmType

data class UiFilm(
    override val id: Long,
    val director: String,
    val stars: String,
    val imdbRating: Double,
    val durationMinutes: Int,
    val releaseYear: Int,
    val filmType: FilmType,
    val description: String,
    override val pictureUri: String,
    override val title: String,
    override val mediaType: ContentType = ContentType.FILM,
    override val localVideoPath: String? = null
) : UiMedia