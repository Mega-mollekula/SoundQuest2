package com.megamollekula.soundquest2.domain.repository

import com.megamollekula.soundquest2.domain.model.content.Song
import com.megamollekula.soundquest2.domain.model.Result
import com.megamollekula.soundquest2.domain.model.enums.Era
import com.megamollekula.soundquest2.domain.model.enums.Genre

interface SongRepository {
    suspend fun getAllSongs(forceRefresh: Boolean = false, language: String = "ru"): Result<List<Song>>
    suspend fun getRandomSongs(forceRefresh: Boolean, language: String = "ru", count: Int = 20): Result<List<Song>>
    suspend fun getSongsByEra(era: Era, forceRefresh: Boolean = false, language: String = "ru"): Result<List<Song>>
    suspend fun getSongsByGenre(genre: Genre, forceRefresh: Boolean = false, language: String = "ru"): Result<List<Song>>
}