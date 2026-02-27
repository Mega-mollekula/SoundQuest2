package com.megamollekula.soundquest2.data.local.download

import androidx.core.net.toUri
import com.megamollekula.soundquest2.data.remote.api.ApiService
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableGameVideo(
    private val gameId: Long,
    private val videoPath: String,
    private val videoDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(videoDir, "${gameId}_game_video.mp4")

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

            daos.gameMediaDao.updateLocalVideoUri(
                gameId = gameId,
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
