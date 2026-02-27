package com.megamollekula.soundquest2.data.local.download

import androidx.core.net.toUri
import com.megamollekula.soundquest2.data.remote.api.ApiService
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableFilmVideo(
    private val filmId: Long,
    private val videoPath: String,
    private val videoDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(videoDir, "${filmId}_film_video.mp4")

        return try {
            if (destination.exists()) {
                return DownloadResult.Skipped
            }
            if (!destination.exists()) {
                val ok = apiService.downloadVideoMedia(videoPath, destination)
                if (!ok) {
                    return DownloadResult.Failed(null)
                }
            }

            val localUri = destination.toUri().toString()

            daos.filmMediaDao.updateLocalVideoUri(
                filmId = filmId,
                localVideoUri = localUri
            )

            DownloadResult.Success
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            DownloadResult.Failed(e)
        }
    }
}
