package com.example.soundquest2.domain.model.film

data class FilmMedia(
    val duration: Int,
    val localAudioPath: String?,
    val localVideoPath: String?,
    val pictureUri: String,
)
