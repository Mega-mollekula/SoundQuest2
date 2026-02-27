package com.megamollekula.soundquest2.data.remote.dto.songs

import com.megamollekula.soundquest2.domain.model.enums.SegmentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongAudioMediaDto (
    @SerialName("segment")
    val segmentType: SegmentType,
    val duration: Int,
    @SerialName("audio_path")
    val audioPath: String
)