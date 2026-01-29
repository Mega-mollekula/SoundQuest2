package com.example.soundquest2.domain.util

import com.example.soundquest2.domain.model.content.Film
import com.example.soundquest2.domain.model.content.Game
import com.example.soundquest2.domain.model.content.MediaContent
import com.example.soundquest2.domain.model.content.Song

object MediaContentBalancer {

    fun balance(
        games: List<Game>,
        films: List<Film>,
        songs: List<Song>,
        multiplier: Int = 1
    ): List<MediaContent> {

        if (games.isEmpty() || films.isEmpty() || songs.isEmpty()) return emptyList()

        val minSize = minOf(
            games.size,
            films.size,
            songs.size
        ) * multiplier

        val result = mutableListOf<MediaContent>()

        result += games.shuffled().take(minSize)
        result += films.shuffled().take(minSize)
        result += songs.shuffled().take(minSize)

        return result.shuffled()
    }
}