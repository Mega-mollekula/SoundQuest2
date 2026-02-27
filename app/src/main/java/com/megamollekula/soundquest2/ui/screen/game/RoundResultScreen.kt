package com.megamollekula.soundquest2.ui.screen.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.megamollekula.soundquest2.R
import com.megamollekula.soundquest2.ui.playback.player.VideoPlayer
import com.megamollekula.soundquest2.ui.component.ActionButton
import com.megamollekula.soundquest2.ui.component.ResultInfoCard
import com.megamollekula.soundquest2.ui.component.VideoBackground
import com.megamollekula.soundquest2.ui.component.details.FilmDetailsMenu
import com.megamollekula.soundquest2.ui.component.details.GameDetailsMenu
import com.megamollekula.soundquest2.ui.component.details.SongDetailsMenu
import com.megamollekula.soundquest2.ui.intent.GameIntent
import com.megamollekula.soundquest2.ui.model.UiFilm
import com.megamollekula.soundquest2.ui.model.UiGame
import com.megamollekula.soundquest2.ui.model.UiSong
import com.megamollekula.soundquest2.ui.state.GameUiState

@Composable
fun RoundResultScreen(
    videoPlayer: VideoPlayer,
    state: GameUiState.Result,
    onIntent: (GameIntent) -> Unit,
) {

    LaunchedEffect(state.correct) {
        onIntent(GameIntent.ScreenShown)
    }

    var isDetailsExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        VideoBackground(videoPlayer, state)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            ActionButton(
                stringResource(R.string.continue_button),
                onAction = {
                    onIntent(GameIntent.NextRound)
                }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            ResultInfoCard(
                title = state.correct.title,
                onFavouriteClick = {

                },
                onExpandClick = { isDetailsExpanded = true },
                isCorrect = state.isCorrect
            )
        }

        AnimatedVisibility(
            visible = isDetailsExpanded,
            enter = slideInVertically { it },
            exit = slideOutVertically { it }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.95f))
            ) {

                when (state.correct) {
                    is UiFilm -> FilmDetailsMenu(state.correct)
                    is UiGame -> GameDetailsMenu(state.correct)
                    is UiSong -> SongDetailsMenu(state.correct)
                }

                IconButton(
                    onClick = { isDetailsExpanded = false },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Collapse details",
                        tint = Color.White,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}