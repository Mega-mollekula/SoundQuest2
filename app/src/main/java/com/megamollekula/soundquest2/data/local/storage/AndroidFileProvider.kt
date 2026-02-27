package com.megamollekula.soundquest2.data.local.storage

import android.content.Context
import com.megamollekula.soundquest2.domain.repository.FileProvider
import java.io.File

class AndroidFileProvider(private val context: Context) : FileProvider {
    override fun getAudioBaseDir(): File {
        return context.getExternalFilesDir("audios") ?: context.filesDir
    }

    override fun getVideoBaseDir(): File {
        return context.getExternalFilesDir("videos")
            ?: File(context.filesDir, "videos")
    }

    override fun clearAudio() {
        getAudioBaseDir().deleteRecursively()
    }

    override fun clearVideos() {
        getVideoBaseDir().deleteRecursively()
    }
}