package com.megamollekula.soundquest2.domain.usecase

import com.megamollekula.soundquest2.domain.model.DownloadProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.megamollekula.soundquest2.domain.model.Result
import com.megamollekula.soundquest2.domain.repository.MediaDownloader
import com.megamollekula.soundquest2.domain.toAppError
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class DownloadMediaUseCase @Inject constructor(
    private val downloader: MediaDownloader
) {
    operator fun invoke(
        parallelism: Int = 3
    ): Flow<Result<DownloadProgress>> {
        return downloader.downloadAll(parallelism)
            .map<DownloadProgress, Result<DownloadProgress>> { progress ->
                Result.Success(progress)
            }
            .catch { throwable ->
                emit(Result.Error(throwable.toAppError()))
            }
    }
}
