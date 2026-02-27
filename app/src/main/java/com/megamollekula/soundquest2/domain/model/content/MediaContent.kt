package com.megamollekula.soundquest2.domain.model.content

import com.megamollekula.soundquest2.domain.model.enums.ContentType

sealed interface MediaContent {
    val id: Long
    val contentType: ContentType
    fun getVideoPath(): String?
}