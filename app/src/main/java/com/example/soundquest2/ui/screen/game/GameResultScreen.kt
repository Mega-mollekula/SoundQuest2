package com.example.soundquest2.ui.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.component.MainBackground
import com.example.soundquest2.ui.component.MainButton
import com.example.soundquest2.ui.state.GameUiState
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.theme.LocalAppImages
import com.example.soundquest2.ui.util.localizedName
import com.example.soundquest2.ui.component.ResultRow
import com.example.soundquest2.ui.intent.GameIntent
import com.example.soundquest2.ui.util.formatTimestamp

@Composable
fun GameResultScreen(
    state: GameUiState.Finished,
    onIntent: (GameIntent) -> Unit,
    toMainScreen: () -> Unit
) {
    val result = state.gameResult

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        MainBackground(LocalAppImages.current.bg)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = stringResource(R.string.game_result_title),
                    style = AppTypography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "${result.guessedSongsCount} / ${result.roundsCount}",
                        style = AppTypography.titleLarge,
                        color = Color.White
                    )

                    Text(
                        text = stringResource(R.string.guessed),
                        style = AppTypography.bodyLarge,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    ResultRow(
                        title = stringResource(R.string.game_mode),
                        value = result.gameMode.localizedName()
                    )

                    ResultRow(
                        title = stringResource(R.string.rounds_count),
                        value = result.roundsCount.toString()
                    )

                    ResultRow(
                        title = stringResource(R.string.time),
                        value = formatTimestamp(result.createdAt)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MainButton(stringResource(R.string.to_main_menu)) {
                        onIntent(GameIntent.Reset)
                        toMainScreen()
                    }
                }
            }
        }
    }
}