package com.example.soundquest2.data.local.entity.song.bundle

import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.SongMediaEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity

data class SongEntitiesBundle(
    val song: SongEntity,
    val artist: ArtistEntity,
    val artistTranslations: List<ArtistTranslationEntity>,
    val songTranslations: List<SongTranslationEntity>,
    val songMedia: List<SongMediaEntity>
)