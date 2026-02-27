package com.megamollekula.soundquest2.domain.model.content

import com.megamollekula.soundquest2.domain.model.enums.ContentType
import com.megamollekula.soundquest2.domain.model.enums.Era
import com.megamollekula.soundquest2.domain.model.enums.Genre
import com.megamollekula.soundquest2.domain.model.enums.SegmentType
import com.megamollekula.soundquest2.domain.model.song.Artist
import com.megamollekula.soundquest2.domain.model.song.SongAudioMedia
import com.megamollekula.soundquest2.domain.model.song.SongTranslation
import com.megamollekula.soundquest2.domain.model.song.SongVisualMedia

data class Song (
    override val id: Long,
    val genre: Genre,
    val era: Era,
    val title: String,
    val songTranslations: List<SongTranslation>,
    val audioMedia: List<SongAudioMedia>,
    val visualMedia: SongVisualMedia,
    val artist: Artist,
    override val contentType: ContentType = ContentType.SONG
) : MediaContent {

    fun getAudioByType(segmentType: SegmentType): SongAudioMedia {
        return audioMedia.filter { it.segmentType == segmentType }.random()
    }

    override fun getVideoPath(): String? {
        return visualMedia.localVideoPath
    }
}