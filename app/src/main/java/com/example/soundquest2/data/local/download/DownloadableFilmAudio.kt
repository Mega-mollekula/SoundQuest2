package com.example.soundquest2.data.local.download

import androidx.core.net.toUri
import com.example.soundquest2.data.remote.api.ApiService
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableFilmAudio(
    private val filmId: Long,
    private val audioPath: String,
    private val audioDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(audioDir, "${filmId}_film_audio.mp3")

        return try {
            if (destination.exists()) {
                return DownloadResult.Skipped
            }
            if (!destination.exists()) {
                val ok = apiService.downloadAudioMedia(audioPath, destination)
                if (!ok) {
                    return DownloadResult.Failed(null)
                }
            }

            val localUri = destination.toUri().toString()

            daos.filmMediaDao.updateLocalAudioUri(
                filmId = filmId,
                localAudioUri = localUri
            )

            DownloadResult.Success
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            DownloadResult.Failed(e)
        }
    }
}
