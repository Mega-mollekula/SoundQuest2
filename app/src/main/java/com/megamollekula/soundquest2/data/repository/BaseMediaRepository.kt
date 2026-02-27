package com.megamollekula.soundquest2.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.megamollekula.soundquest2.domain.model.Result
import com.megamollekula.soundquest2.domain.toAppError

abstract class BaseMediaRepository {

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
                Result.Error(e.toAppError())
            }
        }
    }
}