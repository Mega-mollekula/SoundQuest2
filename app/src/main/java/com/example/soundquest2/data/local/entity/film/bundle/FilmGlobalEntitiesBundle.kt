package com.example.soundquest2.data.local.entity.film.bundle

import com.example.soundquest2.data.local.entity.film.FilmEntity
import com.example.soundquest2.data.local.entity.film.FilmMediaEntity
import com.example.soundquest2.data.local.entity.film.FilmTranslationEntity

data class FilmGlobalEntitiesBundle(
    val films: List<FilmEntity>,
    val translations: List<FilmTranslationEntity>,
    val media: List<FilmMediaEntity>
)
