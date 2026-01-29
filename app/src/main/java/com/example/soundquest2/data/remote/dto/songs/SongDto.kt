package com.example.soundquest2.data.remote.dto.songs

import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongDto(
    val id: Long,
    val genre: Genre,
    val era: Era,
    val title: String,

    @SerialName("song_translations")
    val songTranslations: List<SongTranslationDto>,
    @SerialName("song_audio_media")
    val audioMedia: List<SongAudioMediaDto>,
    @SerialName("song_visual_media")
    val visualMedia: SongVisualMediaDto,
    val artist: ArtistDto
)
