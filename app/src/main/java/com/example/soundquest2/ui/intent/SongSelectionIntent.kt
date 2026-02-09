package com.example.soundquest2.ui.intent

import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre

sealed interface SongSelectionIntent {
    data class StartClicked(
        val era: Era?,
        val genre: Genre?
    ) : SongSelectionIntent

    data object ExitClicked : SongSelectionIntent
}