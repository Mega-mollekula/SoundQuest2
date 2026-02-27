package com.megamollekula.soundquest2.data.local.download

import com.megamollekula.soundquest2.data.remote.api.ApiService

sealed class DownloadableMedia{

    abstract suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult
}