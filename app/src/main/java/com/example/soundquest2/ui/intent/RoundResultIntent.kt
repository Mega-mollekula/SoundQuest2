package com.example.soundquest2.ui.intent

sealed interface RoundResultIntent {
    data object ContinueClicked : RoundResultIntent
    data object FavouriteClicked : RoundResultIntent
}