package com.example.soundquest2.domain.model

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