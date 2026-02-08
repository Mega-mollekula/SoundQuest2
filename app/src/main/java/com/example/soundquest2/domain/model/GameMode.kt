package com.example.soundquest2.domain.model

import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre

sealed class GameMode {

    data object GuessFilm : GameMode()

    data object GuessGame : GameMode()

    data class GuessSong(
        val era: Era? = null,
        val genre: Genre? = null
    ) : GameMode()

    data object FastStart : GameMode()
}