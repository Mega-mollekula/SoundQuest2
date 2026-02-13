package com.example.soundquest2.ui.screen.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.ui.intent.GameIntent
import com.example.soundquest2.ui.state.GameUiState

@Composable
fun GameScreen(
    state: GameUiState,
    videoPlayer: VideoPlayer,
    onIntent: (GameIntent) -> Unit,
    onExitGame: () -> Unit
) {
    LaunchedEffect(Unit) {
        onIntent(GameIntent.Start)
    }

    when (state) {

        GameUiState.Idle -> {
        }

        is GameUiState.Round -> {
            RoundScreen(
                state = state,
                onIntent = onIntent
            )
        }

        is GameUiState.Result -> {
            RoundResultScreen(
                state = state,
                videoPlayer = videoPlayer,
                onIntent = onIntent
            )
        }

        is GameUiState.Finished -> {
            GameResultScreen(
                state = state,
                toMainScreen = onExitGame,
                onIntent = onIntent
            )
        }

        is GameUiState.Error -> {
            GameErrorScreen(uiState = state, onExit = onExitGame)
        }
    }
}
