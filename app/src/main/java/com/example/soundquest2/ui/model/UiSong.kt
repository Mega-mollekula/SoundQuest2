package com.example.soundquest2.ui.model

import com.example.soundquest2.domain.model.enums.ContentType
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre

data class UiSong(
    override val id: Long,
    val genre: Genre,
    val era: Era,
    override val title: String,
    val info: String,
    override val pictureUri: String,
    val artist: UiArtist,
    override val mediaType: ContentType = ContentType.SONG,
    override val localVideoPath: String? = null
) : UiMedia