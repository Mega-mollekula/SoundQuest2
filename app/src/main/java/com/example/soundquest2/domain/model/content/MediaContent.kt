package com.example.soundquest2.domain.model.content

import com.example.soundquest2.domain.model.enums.ContentType

sealed interface MediaContent {
    val contentType: ContentType
    fun hasVideoPath(): Boolean
}