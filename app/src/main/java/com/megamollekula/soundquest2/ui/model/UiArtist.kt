package com.megamollekula.soundquest2.ui.model

import com.megamollekula.soundquest2.domain.model.enums.Gender

data class UiArtist (
    val countryCode: String,
    val gender: Gender,
    val pictureUri: String,
    val name: String,
    val info: String
)