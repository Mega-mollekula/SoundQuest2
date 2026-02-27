package com.megamollekula.soundquest2.data.local.download

import com.megamollekula.soundquest2.data.remote.api.ApiService
import com.megamollekula.soundquest2.domain.model.DownloadProgress
import com.megamollekula.soundquest2.domain.repository.MediaDownloader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.cancellation.CancellationException

class UnifiedMediaDownloader(
    private val apiService: ApiService,
    private val daos: MediaDaos,
    private val audioDir: File,
    private val videoDir: File,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MediaDownloader {

    init {
        audioDir.mkdirs()
        videoDir.mkdirs()
    }

    override fun downloadAll(parallelism: Int): Flow<DownloadProgress> = channelFlow {

        val allMedia = buildList<DownloadableMedia> {

            // Films
            daos.filmMediaDao.getFilmMediaWithoutLocalAudioPath()
                .forEach {
                    add(
                        DownloadableFilmAudio(
                            it.filmId,
                            it.audioPath,
                            audioDir
                        )
                    )
                }

            daos.filmMediaDao.getFilmMediaWithoutLocalVideoPath()
                .forEach {
                    add(
                        DownloadableFilmVideo(
                            it.filmId,
                            it.videoPath,
                            videoDir
                        )
                    )
                }

            // Games
            daos.gameMediaDao.getGameMediaWithoutLocalAudioPath()
                .forEach {
                    add(
                        DownloadableGameAudio(
                            it.gameId,
                            it.audioPath,
                            audioDir
                        )
                    )
                }

            daos.gameMediaDao.getGameMediaWithoutLocalVideoPath()
                .forEach {
                    add(
                        DownloadableGameVideo(
                            it.gameId,
                            it.videoPath,
                            videoDir
                        )
                    )
                }

            // Songs audio
            daos.songAudioMediaDao.getAudioMediaWithoutLocalAudioPath()
                .forEach {
                    add(
                        DownloadableSongAudio(
                            id = it.id,
                            songId = it.songId,
                            segment = it.segment,
                            audioPath = it.audioPath,
                            audioDir = audioDir
                        )
                    )
                }

            // Songs video
            daos.songVisualMediaDao.getVisualMediaWithoutLocalVideoPath()
                .forEach {
                    add(
                        DownloadableSongVideo(
                            it.songId,
                            it.videoPath!!,
                            videoDir
                        )
                    )
                }
        }

        val total = allMedia.size
        if (total == 0) {
            send(DownloadProgress.Completed(0, 0, 0, 0))
            return@channelFlow
        }

        val completed = AtomicInteger(0)
        val failed = AtomicInteger(0)
        val skipped = AtomicInteger(0)
        val semaphore = Semaphore(parallelism)

        coroutineScope {
            allMedia.map { media ->
                launch(dispatcher) {
                    semaphore.withPermit {
                        val result = try {
                            media.download(apiService, daos)
                        } catch (e: CancellationException) { // обработка отдельно, вдруг буду делать обертку
                            throw e
                        } catch (e: Exception) {
                            throw e
                        }

                        when (result) {
                            is DownloadResult.Success -> completed.incrementAndGet()

                            is DownloadResult.Failed -> failed.incrementAndGet()

                            is DownloadResult.Skipped -> skipped.incrementAndGet()
                        }

                        send(
                            DownloadProgress.InProgress(
                                completed = completed.get(),
                                failed = failed.get(),
                                skipped = skipped.get(),
                                total = total
                            )
                        )
                    }
                }
            }.joinAll()
        }

        send(
            DownloadProgress.Completed(
                completed = completed.get(),
                failed = failed.get(),
                skipped = skipped.get(),
                total = total
            )
        )
    }
}
