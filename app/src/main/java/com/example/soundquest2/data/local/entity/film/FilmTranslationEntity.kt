package com.example.soundquest2.data.local.entity.film

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "film_translations",
    primaryKeys = ["film_id", "language"],
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = ["id"],
            childColumns = ["film_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("film_id")]
)
data class FilmTranslationEntity(
    @ColumnInfo("film_id")
    val filmId: Long,
    val language: String,
    val description: String,
    val title: String
)
