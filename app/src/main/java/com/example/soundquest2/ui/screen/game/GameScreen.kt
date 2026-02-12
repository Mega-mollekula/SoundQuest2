package com.example.soundquest2.ui.screen.game

import androidx.compose.runtime.Composable
import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.ui.intent.RoundIntent
import com.example.soundquest2.ui.intent.RoundResultIntent
import com.example.soundquest2.ui.state.GameUiState

@Composable
fun GameScreen(
    state: GameUiState,
    videoPlayer: VideoPlayer,
    onRoundIntent: (RoundIntent) -> Unit,
    onRoundResultIntent: (RoundResultIntent) -> Unit,
    onExitGame: () -> Unit
) {
    when (state) {

        GameUiState.Idle -> {
        }

        is GameUiState.Round -> {
            RoundScreen(
                state = state,
                onIntent = onRoundIntent
            )
        }

        is GameUiState.Result -> {
            RoundResultScreen(
                state = state,
                videoPlayer = videoPlayer,
                onIntent = onRoundResultIntent
            )
        }

        is GameUiState.Finished -> {
            GameResultScreen(
                state = state,
                toMainScreen = onExitGame
            )
        }

        is GameUiState.Error -> {
            GameErrorScreen(uiState = state, onExit = onExitGame)
        }
    }
}
