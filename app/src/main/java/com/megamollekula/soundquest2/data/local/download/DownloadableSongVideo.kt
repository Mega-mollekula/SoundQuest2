package com.megamollekula.soundquest2.data.local.download

import androidx.core.net.toUri
import com.megamollekula.soundquest2.data.remote.api.ApiService
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableSongVideo(
    private val songId: Long,
    private val videoPath: String,
    private val videoDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(videoDir, "${songId}_song_video.mp4")

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

            daos.songVisualMediaDao.updateLocalVideoUri(
                songId = songId,
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
