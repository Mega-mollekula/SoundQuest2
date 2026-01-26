package com.example.soundquest2.data.mapper

import com.example.soundquest2.data.local.entity.film.FilmEntity
import com.example.soundquest2.data.local.entity.film.FilmMediaEntity
import com.example.soundquest2.data.local.entity.film.FilmTranslationEntity
import com.example.soundquest2.data.local.entity.film.bundle.FilmEntitiesBundle
import com.example.soundquest2.data.local.entity.film.bundle.FilmGlobalEntitiesBundle
import com.example.soundquest2.data.local.entity.game.GameEntity
import com.example.soundquest2.data.local.entity.game.GameMediaEntity
import com.example.soundquest2.data.local.entity.game.GameTranslationEntity
import com.example.soundquest2.data.local.entity.game.bundle.GameEntitiesBundle
import com.example.soundquest2.data.local.entity.game.bundle.GameGlobalEntitiesBundle
import com.example.soundquest2.data.local.entity.song.ArtistEntity
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.example.soundquest2.data.local.entity.song.bundle.SongEntitiesBundle
import com.example.soundquest2.data.local.entity.song.SongEntity
import com.example.soundquest2.data.local.entity.song.bundle.SongGlobalBundle
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongVisualMediaEntity
import com.example.soundquest2.data.remote.dto.films.FilmDto
import com.example.soundquest2.data.remote.dto.games.GameDto
import com.example.soundquest2.data.remote.dto.songs.SongDto

fun SongDto.toEntities(): SongEntitiesBundle {
    return SongEntitiesBundle(
        song = SongEntity(
            id = id,
            title = title,
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
                info = it.info
            )
        },
        audioMedia = audioMedia.map {
            SongAudioMediaEntity(
                songId = id,
                segment = it.segmentType,
                duration = it.duration,
                audioPath = it.audioPath
            )
        },
        visualMedia = SongVisualMediaEntity(
            songId = id,
            pictureUri = visualMedia.pictureUri,
            videoPath = visualMedia.videoPath,
        )
    )
}

fun List<SongEntitiesBundle>.toGlobalSongBundle(): SongGlobalBundle {
    return SongGlobalBundle(
        songs = map { it.song },
        artists = map { it.artist }.distinctBy { it.id },
        artistTranslations = flatMap { it.artistTranslations },
        songTranslations = flatMap { it.songTranslations },
        audioMedia = flatMap { it.audioMedia },
        visualMedia = map {it.visualMedia}
    )
}

fun GameDto.toEntities(): GameEntitiesBundle {
    return GameEntitiesBundle(
        game = GameEntity(
            id = id,
            developer = developer,
            publisher = publisher,
            releaseYear = releaseYear,
            genre = genre,
            title = title
        ),
        translations = gameTranslations.map{
            GameTranslationEntity(
                gameId = id,
                language = it.language,
                description = it.description
            )
        },
        media = GameMediaEntity(
            gameId = id,
            duration = gameMedia.duration,
            audioPath = gameMedia.audioPath,
            videoPath = gameMedia.videoPath,
            pictureUri = gameMedia.pictureUri
        )
    )
}

fun List<GameEntitiesBundle>.toGlobalGameBundle(): GameGlobalEntitiesBundle {
    return GameGlobalEntitiesBundle(
        games = map {it.game},
        translations = flatMap {it.translations},
        media = map {it.media}
    )
}

fun FilmDto.toEntities(): FilmEntitiesBundle {
    return FilmEntitiesBundle(
        film = FilmEntity(
            id = id,
            director = director,
            stars = stars,
            imdbRating = imdbRating,
            durationMinutes = durationMinutes,
            releaseYear = releaseYear,
            filmType = filmType,
            title = title
        ),
        translations = filmTranslations.map {
            FilmTranslationEntity(
                filmId = id,
                language = it.language,
                description = it.description
            )
        },
        media = FilmMediaEntity(
            filmId = id,
            duration = filmMedia.duration,
            audioPath = filmMedia.audioPath,
            videoPath = filmMedia.videoPath,
            pictureUri = filmMedia.pictureUri
        )
    )
}

fun List<FilmEntitiesBundle>.toGlobalFilmBundle(): FilmGlobalEntitiesBundle {
    return FilmGlobalEntitiesBundle(
        films = map {it.film},
        translations = flatMap {it.translations},
        media = map {it.media}
    )
}