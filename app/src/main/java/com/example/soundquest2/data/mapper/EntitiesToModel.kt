package com.example.soundquest2.data.mapper

import com.example.soundquest2.data.local.entity.film.FilmMediaEntity
import com.example.soundquest2.data.local.entity.film.FilmTranslationEntity
import com.example.soundquest2.data.local.entity.film.relation.FilmWithDetails
import com.example.soundquest2.data.local.entity.song.ArtistTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongAudioMediaEntity
import com.example.soundquest2.data.local.entity.song.SongTranslationEntity
import com.example.soundquest2.data.local.entity.song.SongVisualMediaEntity
import com.example.soundquest2.data.local.entity.song.relation.ArtistWithTranslations
import com.example.soundquest2.data.local.entity.song.relation.SongWithDetails
import com.example.soundquest2.domain.model.film.Film
import com.example.soundquest2.domain.model.film.FilmMedia
import com.example.soundquest2.domain.model.film.FilmTranslation
import com.example.soundquest2.domain.model.song.Artist
import com.example.soundquest2.domain.model.song.ArtistTranslation
import com.example.soundquest2.domain.model.song.Song
import com.example.soundquest2.domain.model.song.SongAudioMedia
import com.example.soundquest2.domain.model.song.SongTranslation
import com.example.soundquest2.domain.model.song.SongVisualMedia

fun ArtistWithTranslations.toArtistModel(): Artist {
    return Artist (
        countryCode = artist.countryCode,
        gender = artist.gender,
        pictureUri = artist.pictureUri,
        translations = translations.map {it.toArtistTranslationModel()}
    )
}

fun ArtistTranslationEntity.toArtistTranslationModel(): ArtistTranslation {
    return ArtistTranslation(
        language = language,
        name = name,
        info = info
    )
}

fun SongAudioMediaEntity.toSongAudioModel(): SongAudioMedia {
    return SongAudioMedia(
        segmentType = segment,
        duration = duration,
        localAudioPath = localAudioPath!!
    )
}

fun SongVisualMediaEntity.toSongVisualModel(): SongVisualMedia {
    return SongVisualMedia(
        pictureUri = pictureUri,
        localVideoPath = localVideoPath!!
    )
}

fun SongTranslationEntity.toSongTranslationModel(): SongTranslation {
    return SongTranslation(
        language = language,
        info = info
    )
}

fun SongWithDetails.toSongModel(): Song {
    return Song(
        genre = song.genre,
        era = song.era,
        title = song.title,
        songTranslations = translations.map {it.toSongTranslationModel()},
        audioMedia = audioMedia.map {it.toSongAudioModel()},
        visualMedia = visualMedia.toSongVisualModel(),
        artist = artist.toArtistModel()
    )
}

fun List<SongWithDetails>.toSongModels(): List<Song> {
    return this.map { it.toSongModel() }
}

fun FilmTranslationEntity.toModel(): FilmTranslation {
    return FilmTranslation(
        language = language,
        description = description,
        title = title
    )
}

fun FilmMediaEntity.toModel(): FilmMedia {
    return FilmMedia (
        duration = duration,
        localAudioPath = localAudioPath,
        localVideoPath = localVideoPath,
        pictureUri = pictureUri
    )
}

fun FilmWithDetails.toModel(): Film {
    return Film(
        director = film.director,
        stars = film.stars,
        imdbRating = film.imdbRating,
        durationMinutes = film.durationMinutes,
        releaseYear = film.releaseYear,
        filmType = film.filmType,
        filmTranslations = translations.map {it.toModel()},
        filmMedia = media.toModel()
    )
}

fun List<FilmWithDetails>.toFilmModels(): List<Film> {
    return this.map { it.toModel() }
}
