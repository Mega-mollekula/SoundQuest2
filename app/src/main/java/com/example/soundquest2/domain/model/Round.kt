package com.example.soundquest2.domain.model

import com.example.soundquest2.domain.model.content.MediaContent

data class Round(
    val correct: MediaContent,
    val options: List<MediaContent>
)