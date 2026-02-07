package com.example.soundquest2.ui

import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.domain.model.GameState
import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.GamePhase
import com.example.soundquest2.domain.model.song.Artist
import com.example.soundquest2.ui.model.UiArtist
import com.example.soundquest2.ui.model.UiFilm
import com.example.soundquest2.ui.model.UiGame
import com.example.soundquest2.ui.model.UiMedia
import com.example.soundquest2.ui.model.UiSong
import com.example.soundquest2.ui.state.GameUiState
import java.lang.Exception

fun GameState.toUiState(): GameUiState {
    return when (gamePhase) {

        GamePhase.IDLE -> GameUiState.Idle

        GamePhase.ROUND -> {
            val round = currentRound
                ?: return GameUiState.Error(
                    error = error ?: AppError.Unknown(
                        IllegalStateException("ROUND phase but currentRound == null")
                    )
                )

            GameUiState.Round(
                roundNumber = currentRoundIndex + 1,
                totalRounds = totalRounds,
                options = round.options,
            )
        }

        GamePhase.RESULT -> {
            val round = currentRound
                ?: return GameUiState.Error(
                    error = error ?: AppError.Unknown(
                        IllegalStateException("ROUND phase but currentRound == null")
                    )
                )

            GameUiState.Result(
                isCorrect = isAnswerCorrect ?: false,
                selected = selectedAnswer ?: round.correct,
                correct = round.correct
            )
        }

        GamePhase.FINISHED ->
            GameUiState.Finished(
                gameResult = GameResult(
                    createdAt = System.currentTimeMillis(),
                    roundsCount = totalRounds,
                    guessedSongsCount = score,
                    gameMode = gameMode
                )
            )

        GamePhase.ERROR ->
            GameUiState.Error(
                error = error ?: AppError.Unknown(
                    cause = Exception("Unknown error")
                )
            )
    }
}

fun Artist.toArtistUi(languageCode: String): UiArtist {
    val translation = translations.find { it.language == languageCode } ?: translations.first()

    return UiArtist(
        countryCode = countryCode,
        gender = gender,
        pictureUri = pictureUri,
        name = translation.name.ifBlank { "Unknown" },
        info = translation.info.ifBlank { "Unknown" }
    )
}

fun Song.toSongUi(languageCode: String): UiSong {
    val translation = songTranslations.find { it.language == languageCode } ?: songTranslations.first()

    return UiSong(
        genre = genre,
        era = era,
        title = title,
        info = translation.info,
        pictureUri = visualMedia.pictureUri,
        artist = artist.toArtistUi(languageCode)
    )
}

fun Game.toGameUi(languageCode: String): UiGame {
    val translation = gameTranslations.find{ it.language == languageCode } ?: gameTranslations.first()

    return UiGame(
        developer = developer,
        publisher = publisher,
        releaseYear = releaseYear,
        genre = genre,
        description = translation.description,
        title = title
    )
}

fun Film.toFilmUi(languageCode: String): UiFilm {
    val translation = filmTranslations.find{ it.language == languageCode } ?: filmTranslations.first()

    return UiFilm(
        director = director,
        stars = stars,
        imdbRating = imdbRating,
        durationMinutes = durationMinutes,
        releaseYear = releaseYear,
        filmType = filmType,
        description = translation.description,
        title = translation.title
    )
}

fun MediaContent.toUi(languageCode: String) : UiMedia {

    return when(this) {
        is Song -> this.toSongUi(languageCode)
        is Game -> this.toGameUi(languageCode)
        is Film -> this.toFilmUi(languageCode)
    }
}