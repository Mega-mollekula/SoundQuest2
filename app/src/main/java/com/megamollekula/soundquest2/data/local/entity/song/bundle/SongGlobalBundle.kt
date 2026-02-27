package com.megamollekula.soundquest2.data.local.entity.song.bundle

import com.megamollekula.soundquest2.data.local.entity.song.ArtistEntity
import com.megamollekula.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongTranslationEntity
import com.megamollekula.soundquest2.data.local.entity.song.SongVisualMediaEntity

data class SongGlobalBundle(
    val songs: List<SongEntity>,
    val artists: List<ArtistEntity>,
    val artistTranslations: List<ArtistTranslationEntity>,
    val songTranslations: List<SongTranslationEntity>,
    val audioMedia: List<SongAudioMediaEntity>,
    val visualMedia: List<SongVisualMediaEntity>
)