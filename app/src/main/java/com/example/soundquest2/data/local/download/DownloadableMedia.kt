package com.example.soundquest2.data.local.download

import com.example.soundquest2.data.remote.api.ApiService

sealed class DownloadableMedia{

    abstract suspend fun download(
        apiService: ApiService,
        daos: MediaDaos
    ): DownloadResult
}