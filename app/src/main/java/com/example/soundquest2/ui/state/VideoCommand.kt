package com.example.soundquest2.ui.state

sealed interface VideoCommand {
    data class Prepare(val paths: List<String>) : VideoCommand
    data class Play(val index: Int) : VideoCommand
    object Stop : VideoCommand
}