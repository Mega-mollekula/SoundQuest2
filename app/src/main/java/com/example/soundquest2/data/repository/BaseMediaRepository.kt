package com.example.soundquest2.data.repository

import android.database.sqlite.SQLiteException
import com.example.soundquest2.core.errors.AppError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import com.example.soundquest2.core.errors.Result

abstract class BaseMediaRepository {

    protected fun mapException(e: Throwable): AppError {
        return when (e) {
            is ClientRequestException -> AppError.HttpError(
                code = e.response.status.value,
                message = e.message
            )

            is ServerResponseException -> AppError.HttpError(
                code = e.response.status.value,
                message = e.message
            )

            is NoSuchElementException -> AppError.NoContent(
                description = "No elements from server!"
            )

            is IOException -> AppError.NetworkUnavailable

            is SQLiteException -> AppError.DatabaseError

            else -> AppError.Unknown(e)
        }
    }

    protected suspend fun <Local, Remote, Domain> fetchData(
        forceRefresh: Boolean,
        localFetch: suspend () -> List<Local>,
        remoteFetch: suspend () -> List<Remote>,
        saveRemote: suspend (List<Remote>) -> Unit,
        mapToDomain: (List<Local>) -> List<Domain>
    ): Result<List<Domain>> = withContext(Dispatchers.IO) {

        val localBefore = localFetch()

        try {
            if (forceRefresh || localBefore.isEmpty()) {
                val remote = remoteFetch()
                if (remote.isEmpty()) {
                    throw NoSuchElementException("No content from remote")
                }
                saveRemote(remote)
            }

            Result.Success(mapToDomain(localFetch()))

        } catch (e: Exception) {
            if (localBefore.isNotEmpty()) {
                Result.Success(mapToDomain(localBefore))
            } else {
                Result.Error(mapException(e))
            }
        }
    }
}