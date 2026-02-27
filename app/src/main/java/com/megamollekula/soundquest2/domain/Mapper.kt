package com.megamollekula.soundquest2.domain

import android.database.sqlite.SQLiteException
import com.megamollekula.soundquest2.domain.model.AppError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

fun Throwable.toAppError(): AppError {
    return when (this) {
        is ClientRequestException -> AppError.HttpError(
            code = this.response.status.value,
            message = this.message
        )

        is ServerResponseException -> AppError.HttpError(
            code = this.response.status.value,
            message = this.message
        )

        is NoSuchElementException -> AppError.NoContent(
            description = "No elements from server!"
        )

        is IOException -> AppError.NetworkUnavailable

        is SQLiteException -> AppError.DatabaseError

        else -> AppError.Unknown(this)
    }
}