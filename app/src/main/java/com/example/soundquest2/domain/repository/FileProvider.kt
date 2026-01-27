package com.example.soundquest2.domain.repository

import java.io.File

interface FileProvider {
    fun getAudioBaseDir(): File
    fun getVideoBaseDir(): File
    fun clearAudio()
    fun clearVideos()
}