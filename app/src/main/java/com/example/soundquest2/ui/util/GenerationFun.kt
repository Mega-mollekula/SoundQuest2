package com.example.soundquest2.ui.util

import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.ContentType
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Gender
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.domain.model.enums.SegmentType
import com.example.soundquest2.domain.model.song.Artist
import com.example.soundquest2.domain.model.song.ArtistTranslation
import com.example.soundquest2.domain.model.song.SongAudioMedia
import com.example.soundquest2.domain.model.song.SongTranslation
import com.example.soundquest2.domain.model.song.SongVisualMedia

fun generateRandomSong(): Song {
    val era = Era.entries.random()

    val genre = when (era) {
        Era.ERA_80S -> listOf(Genre.POP, Genre.ROCK, Genre.CLASSIC).random()
        Era.ERA_90S -> listOf(Genre.POP, Genre.RAP, Genre.ROCK).random()
        Era.ERA_2000S -> listOf(Genre.POP, Genre.CLASSIC, Genre.ROCK).random()
        Era.ERA_2010S -> listOf(Genre.POP, Genre.RAP, Genre.CLASSIC).random()
        Era.ERA_2020S -> listOf(Genre.POP, Genre.RAP, Genre.ROCK).random()
    }

    val title = generateSongTitle(genre, era)

    val songTranslations = listOf(
        SongTranslation(
            language = "en",
            info = "A ${
                genre.name.lowercase().replace("_", " ")
            } hit from the ${era.name.lowercase().replace("era_", "")}s."
        ),
        SongTranslation(
            language = "ru",
            info = "${genre.name.lowercase().replace("_", " ")} хит из ${era.name.lowercase().replace("era_", "")}-х."
        )
    )

    val audioMedia = listOf(
        SongAudioMedia(
            localAudioPath = "https://example.com/audio/${title.lowercase().replace(" ", "_")}.mp3".trim(),
            segmentType = SegmentType.INTRO,
            duration = 10
        )
    )

    val visualMedia = SongVisualMedia(
        pictureUri = "https://example.com/covers/${genre.name.lowercase()}_${era.name.lowercase()}.jpg".trim(),
        localVideoPath = "https://example.com/videos/${
            title.lowercase().replace(" ", "_")
        }.mp4".trim()
    )

    val artist = generateArtist(genre, era)

    return Song(
        genre = genre,
        era = era,
        title = title,
        songTranslations = songTranslations,
        audioMedia = audioMedia,
        visualMedia = visualMedia,
        artist = artist,
        contentType = ContentType.SONG
    )
}

private fun generateSongTitle(genre: Genre, era: Era): String {
    val prefixes = when (genre) {
        Genre.ROCK -> listOf("Thunder", "Fire", "Midnight", "Wild")
        Genre.POP -> listOf("Shine", "Dream", "Starlight", "Forever")
        Genre.RAP -> listOf("Street", "King", "Flow", "Legacy")
        Genre.CLASSIC -> listOf("Blue", "Smooth", "Moonlight", "Velvet")
        else -> listOf("Echo", "Silent", "Golden", "Electric")
    }

    val suffixes = when (era) {
        Era.ERA_2000S, Era.ERA_80S -> listOf("Road", "Heart", "Love", "Sky")
        Era.ERA_90S, Era.ERA_2010S -> listOf("Machine", "City", "Neon", "Wave")
        Era.ERA_2020S -> listOf("World", "Life", "Time", "Game")
    }

    return "${prefixes.random()} ${suffixes.random()}"
}

private fun generateArtist(genre: Genre, era: Era): Artist {
    val country = when (genre) {
        Genre.ROCK -> "KR"
        Genre.RAP -> "JM"
        Genre.CLASSIC -> "ES"
        else -> listOf("US", "UK", "CA", "AU").random()
    }

    val gender = Gender.entries.random()

    val artistNameEn = when (genre) {
        Genre.RAP -> "${listOf("MC", "DJ", "The").random()} ${generateRandomWord()}"
        Genre.ROCK -> "${generateRandomWord()} ${listOf("Boys", "Girls").random()}"
        else -> generateRandomWord()
    }

    val artistNameRu = when (genre) {
        Genre.RAP -> "MC ${generateRandomWordRu()}"
        Genre.CLASSIC -> "${generateRandomWordRu()} ${listOf("Боиз", "Гёрлз").random()}"
        else -> generateRandomWordRu()
    }

    return Artist(
        countryCode = country,
        gender = gender,
        pictureUri = "https://example.com/artists/${artistNameEn.lowercase().replace(" ", "_")}.jpg".trim(),
        translations = listOf(
            ArtistTranslation(
                language = "en",
                name = artistNameEn,
                info = "Famous ${genre.name.lowercase()} artist from the ${
                    era.name.lowercase().replace("era_", "")
                }s."
            ),
            ArtistTranslation(
                language = "ru",
                name = artistNameRu,
                info = "Известный исполнитель жанра ${genre.name.lowercase()} из ${era.name.lowercase().replace("era_", "")}-х."
            )
        )
    )
}

private fun generateRandomWord(): String {
    val adjectives = listOf("Crimson", "Silver", "Ocean", "Phantom", "Neon")
    val nouns = listOf("Tiger", "Storm", "Mirror", "Shadow", "Rider")
    return "${adjectives.random()} ${nouns.random()}"
}

private fun generateRandomWordRu(): String {
    val adjectives = listOf("Алый", "Серебряный", "Океан", "Фантом", "Неоновый")
    val nouns = listOf("Тигр", "Шторм", "Зеркало", "Тень", "Наездник")
    return "${adjectives.random()} ${nouns.random()}"
}