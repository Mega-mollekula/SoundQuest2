package com.example.soundquest2.data.local.entity.song.bundle

import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongVisualMediaEntity

data class SongEntitiesBundle(
    val song: SongEntity,
    val artist: ArtistEntity,
    val artistTranslations: List<ArtistTranslationEntity>,
    val songTranslations: List<SongTranslationEntity>,
    val audioMedia: List<SongAudioMediaEntity>,
    val visualMedia: SongVisualMediaEntity
)