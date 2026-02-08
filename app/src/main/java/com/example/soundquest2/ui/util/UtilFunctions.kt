package com.example.soundquest2.ui.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.soundquest2.R
import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.FilmType
import com.example.soundquest2.domain.model.enums.GameGenre
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.ui.model.UiError

@Composable
fun GameMode.localizedName(): String {
    return when (this) {
        GameMode.FastStart ->
            stringResource(R.string.game_mode_fast_start)

        GameMode.GuessFilm ->
            stringResource(R.string.game_mode_guess_film)

        GameMode.GuessGame ->
            stringResource(R.string.game_mode_guess_game)

        is GameMode.GuessSong ->
            stringResource(R.string.game_mode_guess_song)
    }
}

@Composable
fun Era.localizedName(): String {
    return when (this) {
        Era.ERA_80S -> stringResource(R.string.era_80s)
        Era.ERA_90S -> stringResource(R.string.era_90s)
        Era.ERA_2000S -> stringResource(R.string.era_2000s)
        Era.ERA_2010S -> stringResource(R.string.era_2010s)
        Era.ERA_2020S -> stringResource(R.string.era_2020s)
    }
}

@Composable
fun Genre.localizedName(): String {
    return when (this) {
        Genre.ROCK -> stringResource(R.string.genre_rock)
        Genre.POP -> stringResource(R.string.genre_pop)
        Genre.CLASSIC -> stringResource(R.string.genre_classic)
        Genre.RAP -> stringResource(R.string.genre_rap)
        Genre.OTHER -> stringResource(R.string.genre_other)
    }
}

@Composable
fun FilmType.localizedName(): String {
    return when (this) {
        FilmType.ACTION -> stringResource(R.string.film_type_action)
        FilmType.DRAMA -> stringResource(R.string.film_type_drama)
        FilmType.COMEDY -> stringResource(R.string.film_type_comedy)
        FilmType.THRILLER -> stringResource(R.string.film_type_thriller)
        FilmType.HORROR -> stringResource(R.string.film_type_horror)
        FilmType.FANTASY -> stringResource(R.string.film_type_fantasy)
        FilmType.SCIENCE_FICTION -> stringResource(R.string.film_type_science_fiction)
        FilmType.ADVENTURE -> stringResource(R.string.film_type_adventure)
    }
}

@Composable
fun GameGenre.localizedName(): String {
    return when (this) {
        GameGenre.ACTION -> stringResource(R.string.game_genre_action)
        GameGenre.RPG -> stringResource(R.string.game_genre_rpg)
        GameGenre.ADVENTURE -> stringResource(R.string.game_genre_adventure)
        GameGenre.STRATEGY -> stringResource(R.string.game_genre_strategy)
        GameGenre.SHOOTER -> stringResource(R.string.game_genre_shooter)
        GameGenre.SIMULATION -> stringResource(R.string.game_genre_simulation)
        GameGenre.SPORTS -> stringResource(R.string.game_genre_sports)
        GameGenre.PUZZLE -> stringResource(R.string.game_genre_puzzle)
        GameGenre.RUNNER -> stringResource(R.string.game_genre_runner)
    }
}

fun AppError.toUiError(): UiError =
    when (this) {

        is AppError.NetworkUnavailable ->
            UiError(
                titleRes = R.string.error_title,
                messageRes = R.string.error_no_internet,
                iconRes = R.drawable.error_no_internet,
                canRetry = true
            )

        is AppError.HttpError ->
            UiError(
                titleRes = R.string.error_title,
                messageRes = R.string.error_server,
                iconRes = R.drawable.error_server,
                canRetry = true
            )

        is AppError.DatabaseError ->
            UiError(
                titleRes = R.string.error_title,
                messageRes = R.string.error_database,
                iconRes = R.drawable.error_database,
                canRetry = false
            )

        is AppError.Unknown -> {
            Log.e("APP_ERROR", "Unknown error", cause)

            UiError(
                titleRes = R.string.error_title,
                messageRes = R.string.error_unknown,
                iconRes = R.drawable.error_icon,
                canRetry = true
            )
        }

        is AppError.NoContent -> {
            UiError(
                titleRes = R.string.error_title,
                messageRes = R.string.error_empty_content,
                iconRes = R.drawable.error_icon,
                canRetry = true
            )
        }
    }