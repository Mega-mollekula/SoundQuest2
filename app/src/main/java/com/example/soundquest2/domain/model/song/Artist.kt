package com.example.soundquest2.domain.model.song

import com.example.soundquest2.domain.model.enums.Gender

data class Artist(
    val countryCode: String,
    val gender: Gender,
    val pictureUri: String,
    val translations: List<ArtistTranslation>
)
