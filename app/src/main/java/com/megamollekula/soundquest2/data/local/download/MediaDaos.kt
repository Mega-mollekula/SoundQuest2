package com.megamollekula.soundquest2.data.local.download

import com.megamollekula.soundquest2.data.local.dao.film.FilmMediaDao
import com.megamollekula.soundquest2.data.local.dao.game.GameMediaDao
import com.megamollekula.soundquest2.data.local.dao.song.SongAudioMediaDao
import com.megamollekula.soundquest2.data.local.dao.song.SongVisualMediaDao

data class MediaDaos(
    val filmMediaDao: FilmMediaDao,
    val gameMediaDao: GameMediaDao,
    val songAudioMediaDao: SongAudioMediaDao,
    val songVisualMediaDao: SongVisualMediaDao
)