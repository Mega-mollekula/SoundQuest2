package com.example.soundquest2.data.local.entity.film.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.soundquest2.data.local.entity.film.FilmEntity
import com.example.soundquest2.data.local.entity.film.FilmMediaEntity
import com.example.soundquest2.data.local.entity.film.FilmTranslationEntity

data class FilmWithDetails(
    @Embedded
    val film: FilmEntity,

    @Relation (
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val translations: List<FilmTranslationEntity>,

    @Relation (
        parentColumn = "id",
        entityColumn = "film_id"
    )
    val media: FilmMediaEntity
)
