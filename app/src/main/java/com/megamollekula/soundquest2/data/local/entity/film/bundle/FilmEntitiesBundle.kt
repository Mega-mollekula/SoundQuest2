package com.megamollekula.soundquest2.data.local.entity.film.bundle

import com.megamollekula.soundquest2.data.local.entity.film.FilmEntity
import com.megamollekula.soundquest2.data.local.entity.film.FilmMediaEntity
import com.megamollekula.soundquest2.data.local.entity.film.FilmTranslationEntity

data class FilmEntitiesBundle(
    val film: FilmEntity,
    val translations: List<FilmTranslationEntity>,
    val media: FilmMediaEntity
)
