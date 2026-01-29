package com.example.soundquest2.domain.usecase

import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.repository.FilmRepository
import com.example.soundquest2.domain.repository.GameRepository
import com.example.soundquest2.domain.repository.SongRepository
import com.example.soundquest2.core.errors.Result
import com.example.soundquest2.domain.util.MediaContentBalancer

class LoadMediaUseCase(
    private val songRepository: SongRepository,
    private val gameAudioRepository: GameRepository,
    private val filmAudioRepository: FilmRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean, gameMode: GameMode): Result<List<MediaContent>> {
        return when (gameMode) {
            is GameMode.GuessSong -> {
                songRepository.getRandomSongs(forceRefresh)
            }

            is GameMode.GuessSongWithParams -> {
                when {
                    gameMode.genre != null -> {
                        songRepository.getSongsByGenre(gameMode.genre, forceRefresh)
                    }
                    gameMode.era != null -> {
                        songRepository.getSongsByEra(gameMode.era, forceRefresh)
                    }
                    else -> {
                        songRepository.getAllSongs(forceRefresh)
                    }
                }
            }

            is GameMode.GuessFilm -> {
                filmAudioRepository.getRandomFilms(forceRefresh)
            }

            is GameMode.GuessGame -> {
                gameAudioRepository.getRandomGames(forceRefresh)
            }

            is GameMode.FastStart -> {
                loadFastStart(forceRefresh) // with balance
            }
        }
    }

    private suspend fun loadFastStart(
        forceRefresh: Boolean
    ): Result<List<MediaContent>> {

        val songsResult = songRepository.getRandomSongs(forceRefresh)
        val filmsResult = filmAudioRepository.getRandomFilms(forceRefresh)
        val gamesResult = gameAudioRepository.getRandomGames(forceRefresh)

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