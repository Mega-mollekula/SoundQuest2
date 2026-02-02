package com.example.soundquest2.data.local.download

import android.util.Log
import com.example.soundquest2.data.remote.api.ApiService
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
                    Log.d("DownloadError", "Error to download $videoPath")
                    return DownloadResult.Failed(null)
                }
            }

            daos.filmMediaDao.updateLocalVideoPath(
                filmId, destination.absolutePath
            )
            Log.d("DownloadTest", "Скачано по пути ${destination.absolutePath} ")
            DownloadResult.Success
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            DownloadResult.Failed(e)
        }
    }
}
