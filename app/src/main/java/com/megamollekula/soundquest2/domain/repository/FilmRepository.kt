package com.megamollekula.soundquest2.domain.repository

import com.megamollekula.soundquest2.domain.model.content.Film
import com.megamollekula.soundquest2.domain.model.Result

interface FilmRepository {
    suspend fun getAllFilms(forceRefresh: Boolean, language: String = "ru"): Result<List<Film>>
    suspend fun getRandomFilms(forceRefresh: Boolean, count: Int = 20, language: String = "ru"): Result<List<Film>>
}