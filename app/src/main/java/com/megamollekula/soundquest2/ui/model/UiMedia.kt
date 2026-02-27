package com.megamollekula.soundquest2.ui.model

import com.megamollekula.soundquest2.domain.model.enums.ContentType

sealed interface UiMedia {
    val id: Long
    val mediaType: ContentType
    val title: String
    val pictureUri: String
    val localVideoPath: String?
}