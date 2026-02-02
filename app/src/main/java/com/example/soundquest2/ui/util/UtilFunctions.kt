package com.example.soundquest2.ui.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.soundquest2.R
import com.example.soundquest2.core.errors.AppError
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.ui.model.UiError
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Color.makeWarmer(
    warmth: Float = 0.2f
): Color {
    val clampedWarmth = warmth.coerceIn(0f, 1f)

    val r = red + (1f - red) * clampedWarmth
    val g = green + (0.2f * clampedWarmth)
    val b = blue * (1f - clampedWarmth)

    return Color(
        red = r.coerceIn(0f, 1f),
        green = g.coerceIn(0f, 1f),
        blue = b.coerceIn(0f, 1f),
        alpha = alpha
    )
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@Composable
fun GameMode.localizedName(): String {
    return when (this) {
        GameMode.FastStart -> stringResource(R.string.fast_start)
        GameMode.GuessFilm -> stringResource(R.string.guess_film)
        GameMode.GuessGame -> stringResource(R.string.guess_game)
        GameMode.GuessSong -> stringResource(R.string.guess_song)
        is GameMode.GuessSongWithParams -> stringResource(R.string.guess_song)
    }
}

@Composable
fun Era.localizedName(): String {
    return when (this) {
        Era.ERA_80S -> stringResource(R.string.era_old)
        Era.ERA_90S -> stringResource(R.string.era_revolution)
        Era.ERA_2000S -> stringResource(R.string.era_evolution)
        Era.ERA_2010S -> stringResource(R.string.era_innovation)
        Era.ERA_2020S -> stringResource(R.string.era_innovation)
    }
}

@Composable
fun Genre.localizedName(): String {
    return when (this) {
        Genre.ROCK -> stringResource(R.string.genre_rock)
        Genre.POP -> stringResource(R.string.genre_pop)
        Genre.CLASSIC -> stringResource(R.string.genre_classic)
        Genre.RAP -> stringResource(R.string.genre_rap)
        Genre.OTHER -> stringResource(R.string.genre_rap)
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