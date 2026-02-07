package com.example.soundquest2.ui.model

import com.example.soundquest2.domain.model.enums.ContentType

sealed interface UiMedia {
    val mediaType: ContentType
    val title: String
}