package com.example.soundquest2.data.remote.dto

import com.example.soundquest2.domain.model.Era
import com.example.soundquest2.domain.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongDto(
    val id: Long,
    @SerialName("picture_uri")
    val pictureUri: String,
    val genre: Genre,
    val era: Era,

    @SerialName("song_translations")
    val songTranslations: List<SongTranslationDto>,
    @SerialName("song_media")
    val songMedia: List<SongMediaDto>,
    val artist: ArtistDto
)
