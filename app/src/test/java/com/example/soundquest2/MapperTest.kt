package com.example.soundquest2

import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.content.Song
import com.example.soundquest2.domain.model.enums.ContentType
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Gender
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.domain.model.song.Artist
import com.example.soundquest2.domain.model.song.ArtistTranslation
import com.example.soundquest2.domain.model.song.SongTranslation
import com.example.soundquest2.domain.model.song.SongVisualMedia
import com.example.soundquest2.ui.toSongUi
import com.example.soundquest2.ui.toUi
import org.junit.Test

class MapperTest {
    @Test
    fun mapperModelToUiTest() {

        val song = Song(
            genre = Genre.ROCK,
            era = Era.ERA_80S,
            title = "My Song",
            songTranslations = listOf(
                SongTranslation(
                    language = "en",
                    info = "En Song Info"
                ),
                SongTranslation(
                    language = "ru",
                    info = "Ru Song Info"
                )
            ),
            audioMedia = listOf(),
            visualMedia = SongVisualMedia(
                pictureUri = "https://example.com/picture.jpg",
                localVideoPath = "https://example.com/video.mp3",
            ),
            artist = Artist(
                countryCode = "US",
                gender = Gender.MALE,
                pictureUri = "https://example.com/artist_picture.jpg",
                translations = listOf(
                    ArtistTranslation(
                        language = "en",
                        info = "En Artist Info",
                        name = "En Artist Name"
                    ),
                    ArtistTranslation(
                        language = "ru",
                        info = "Ru Artist Info",
                        name = "Ru Artist Name"
                    )
                ),
            ),
            contentType = ContentType.SONG,
        )

        val uiSong = song.toSongUi("en")

        assert(uiSong.genre == Genre.ROCK)
        assert(uiSong.era == Era.ERA_80S)
        assert(uiSong.title == "My Song")
        assert(uiSong.info == "En Song Info")
        assert(uiSong.pictureUri == "https://example.com/picture.jpg")
        assert(uiSong.artist.countryCode == "US")
        assert(uiSong.artist.gender == Gender.MALE)

        println(uiSong)

        val mediaList: List<MediaContent> = listOf(song)

        println(mediaList.first().toUi("ru"))
    }
}