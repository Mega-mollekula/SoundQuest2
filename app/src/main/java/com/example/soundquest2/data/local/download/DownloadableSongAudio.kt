package com.example.soundquest2.data.local.download

import android.util.Log
import com.example.soundquest2.data.remote.api.ApiService
import com.example.soundquest2.domain.model.SegmentType
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

        val destination = File(
            audioDir,
            "${songId}_${segment.name}.mp3"
        )

        return try {
            if (!destination.exists()) {
                val ok = apiService.downloadAudioMedia(audioPath, destination)
                if (!ok) {
                    Log.d("DownloadError", "Error to download $audioPath")
                    return DownloadResult.Failed(null)
                }
            }

            daos.songAudioMediaDao.updateLocalAudioPath(
                id, destination.absolutePath
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
