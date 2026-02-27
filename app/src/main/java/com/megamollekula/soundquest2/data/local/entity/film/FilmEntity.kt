package com.megamollekula.soundquest2.data.local.entity.film

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.megamollekula.soundquest2.domain.model.enums.FilmType

@Entity(tableName = "films")
data class FilmEntity (
    @PrimaryKey
    val id: Long,
    val director: String,
    val stars: String,
    @ColumnInfo("imdb_rating")
    val imdbRating: Double,
    @ColumnInfo("duration_minutes")
    val durationMinutes: Int,
    @ColumnInfo("release_year")
    val releaseYear: Int,
    @ColumnInfo("film_type")
    val filmType: FilmType,
)