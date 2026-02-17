package com.example.soundquest2.data.local.download

import androidx.core.net.toUri
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.domain.model.enums.SegmentType
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class DownloadableSongAudio(
    private val id: Long,
    private val songId: Long,
    private val segment: SegmentType,
    private val audioPath: String,
    private val audioDir: File
) : DownloadableMedia() {

    override suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult {

        val destination = File(audioDir, "${songId}_${segment.name}.mp3")

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

            daos.songAudioMediaDao.updateLocalAudioUri(
                audioId = id,
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
