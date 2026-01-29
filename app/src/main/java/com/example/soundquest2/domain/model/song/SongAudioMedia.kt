package com.example.soundquest2.domain.model.song

import com.example.soundquest2.domain.model.enums.SegmentType

data class SongAudioMedia (
    val segmentType: SegmentType,
    val duration: Int,
    val localAudioPath: String?
)