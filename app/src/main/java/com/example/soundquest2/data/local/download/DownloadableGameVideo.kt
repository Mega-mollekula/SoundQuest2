package com.example.soundquest2.data.local.download

import android.util.Log
import com.example.soundquest2.data.remote.api.ApiService
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
            if (!destination.exists()) {
                val ok = apiService.downloadVideoMedia(videoPath, destination)
                if (!ok) {
                    Log.d("DownloadError", "Error to download $videoPath")
                    return DownloadResult.Failed(null)
                }
            }

            daos.gameMediaDao.updateLocalVideoPath(
                gameId, destination.absolutePath
            )

            DownloadResult.Success
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            DownloadResult.Failed(e)
        }
    }
}
