package com.example.soundquest2.domain.usecase

import com.example.soundquest2.data.local.download.DownloadProgress
import com.example.soundquest2.data.local.download.UnifiedMediaDownloader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.soundquest2.domain.model.Result
import com.example.soundquest2.domain.toAppError
import kotlinx.coroutines.flow.catch

class DownloadMediaUseCase(
    private val downloader: UnifiedMediaDownloader
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
