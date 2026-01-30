package com.example.soundquest2.data.mapper

import com.example.soundquest2.data.local.entity.result.GameModeType
import com.example.soundquest2.data.local.entity.result.GameResultEntity
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.GameResult

fun GameResult.toResultEntity(): GameResultEntity {
    return GameResultEntity(
        createdAt = createdAt,
        roundsCount = roundsCount,
        gameMode = gameMode.toEnum(),
        guessedSongsCount = guessedSongsCount
    )
}

fun GameMode.toEnum(): GameModeType {
    return when (this) {
        GameMode.GuessSong -> GameModeType.GUESS_SONG

        GameMode.GuessFilm -> GameModeType.GUESS_FILM

        GameMode.GuessGame -> GameModeType.GUESS_GAME

        GameMode.FastStart -> GameModeType.FAST_START

        is GameMode.GuessSongWithParams -> GameModeType.GUESS_SONG
    }
}