package com.example.soundquest2.domain.model.game

data class GameMedia(
    val duration: Int,
    val localAudioPath: String?,
    val localVideoPath: String?,
    val pictureUri: String
)
