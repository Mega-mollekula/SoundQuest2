package com.example.soundquest2.core.errors

sealed class AppError {

    data class HttpError(
        val code: Int,
        val message: String? = null
    ) : AppError()

    object NetworkUnavailable : AppError()

    object DatabaseError : AppError()

    data class NoContent(
        val description: String
    ) : AppError()

    data class Unknown(val cause: Throwable?) : AppError()
}