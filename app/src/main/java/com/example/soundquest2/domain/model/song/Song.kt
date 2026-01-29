package com.example.soundquest2.domain.model.song

import com.example.soundquest2.domain.model.Era
import com.example.soundquest2.domain.model.Genre

data class Song (
    val genre: Genre,
    val era: Era,
    val title: String,
    val songTranslations: List<SongTranslation>,
    val audioMedia: List<SongAudioMedia>,
    val visualMedia: SongVisualMedia,
    val artist: Artist
)