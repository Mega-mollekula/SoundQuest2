package com.example.soundquest2.ui.intent

sealed interface SoundtracksSelectionIntent {
    data object FilmsSelected : SoundtracksSelectionIntent
    data object GamesSelected : SoundtracksSelectionIntent
    data object BackClicked : SoundtracksSelectionIntent
}