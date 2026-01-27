package com.example.soundquest2.data.local.download

import com.example.soundquest2.data.remote.api.ApiService
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableGameAudio(
    private val gameId: Long,
    private val audioPath: String,
    private val audioDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(audioDir, "${gameId}_game_audio.mp3")

        return try {
            if (!destination.exists()) {
                val ok = apiService.downloadAudioMedia(audioPath, destination)
                if (!ok) return DownloadResult.Failed(null)
            }

            daos.gameMediaDao.updateLocalAudioPath(
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
