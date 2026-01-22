package com.example.soundquest2.data.mapper

import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.bundle.SongEntitiesBundle
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.bundle.SongGlobalBundle
import com.example.soundquest2.data.local.entity.song.SongMediaEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity
import com.example.soundquest2.data.remote.dto.songs.SongDto

fun SongDto.toEntities(): SongEntitiesBundle {
    return SongEntitiesBundle(
        song = SongEntity(
            id = id,
            pictureUri = pictureUri,
            genre = genre,
            era = era,
            artistId = artist.id
        ),
        artist = ArtistEntity(
            id = artist.id,
            countryCode = artist.countryCode,
            gender = artist.gender,
            pictureUri = artist.pictureUri
        ),
        artistTranslations = artist.artistTranslations.map {
            ArtistTranslationEntity(
                artistId = artist.id,
                language = it.language,
                name = it.name,
                info = it.info
            )
        },
        songTranslations = songTranslations.map {
            SongTranslationEntity(
                songId = id,
                language = it.language,
                title = it.title,
                info = it.info
            )
        },
        songMedia = songMedia.map {
            SongMediaEntity(
                songId = id,
                segment = it.segment,
                duration = it.duration,
                audioPath = it.audioPath,
                videoPath = it.videoPath
            )
        }
    )
}

fun List<SongEntitiesBundle>.toGlobalSongBundle(): SongGlobalBundle {
    return SongGlobalBundle(
        songs = map { it.song },
        artists = map { it.artist }.distinctBy { it.id },
        artistTranslations = flatMap { it.artistTranslations },
        songTranslations = flatMap { it.songTranslations },
        songMedia = flatMap { it.songMedia }
    )
}