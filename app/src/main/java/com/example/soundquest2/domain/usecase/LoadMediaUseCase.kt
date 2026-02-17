package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.repository.FilmRepository
import com.example.soundquest2.domain.repository.GameRepository
import com.example.soundquest2.domain.repository.SongRepository
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.domain.util.MediaContentBalancer
import javax.inject.Inject

class LoadMediaUseCase @Inject constructor (
    private val songRepository: SongRepository,
    private val gameRepository: GameRepository,
    private val filmRepository: FilmRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean, gameMode: GameMode, language: String, count: Int = 10): Result<List<MediaContent>> {
        return when (gameMode) {
            is GameMode.GuessSong -> {
                when {
                    gameMode.genre != null -> {
                        songRepository.getSongsByGenre(gameMode.genre, forceRefresh, language)
                    }
                    gameMode.era != null -> {
                        songRepository.getSongsByEra(gameMode.era, forceRefresh, language)
                    }
                    else -> {
                        songRepository.getAllSongs(forceRefresh, language)
                    }
                }
            }

            is GameMode.GuessFilm -> {
                filmRepository.getRandomFilms(forceRefresh, count, language)
            }

            is GameMode.GuessGame -> {
                gameRepository.getRandomGames(forceRefresh, count, language)
            }

            is GameMode.FastStart -> {
                loadFastStart(forceRefresh, language, count) // with balance
            }
        }
    }

    private suspend fun loadFastStart(
        forceRefresh: Boolean,
        language: String,
        count: Int = 10
    ): Result<List<MediaContent>> {

        val songsResult = songRepository.getRandomSongs(forceRefresh, language, count)
        val filmsResult = filmRepository.getRandomFilms(forceRefresh, count, language)
        val gamesResult = gameRepository.getRandomGames(forceRefresh, count, language)

        val error = listOf(songsResult, filmsResult, gamesResult).filterIsInstance<Result.Error>().firstOrNull()?.error

        if (error != null) {
            return Result.Error(error)
        }

        val songs = (songsResult as Result.Success).data
        val films = (filmsResult as Result.Success).data
        val games = (gamesResult as Result.Success).data

        val balanced = MediaContentBalancer.balance(
            games = games,
            films = films,
            songs = songs
        )

        return Result.Success(balanced)
    }
}