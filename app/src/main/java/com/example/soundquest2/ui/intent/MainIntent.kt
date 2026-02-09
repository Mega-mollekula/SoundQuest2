package com.example.soundquest2.ui.intent

sealed interface MainIntent {
    data object SoundtracksClicked : MainIntent
    data object SongsClicked : MainIntent
    data object FastStartClicked : MainIntent
    data object AchievementsClicked : MainIntent
    data object MarketClicked : MainIntent
    data object SettingsClicked : MainIntent
}