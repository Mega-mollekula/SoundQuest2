package com.example.soundquest2.domain.model.content

import com.example.soundquest2.domain.model.enums.ContentType

sealed interface MediaContent {
    val id: Long
    val contentType: ContentType
    fun getVideoPath(): String?
}