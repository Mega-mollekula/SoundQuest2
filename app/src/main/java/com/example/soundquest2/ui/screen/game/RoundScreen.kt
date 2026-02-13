package com.example.soundquest2.ui.screen.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.component.MainBackground
import com.example.soundquest2.ui.component.MainButton
import com.example.soundquest2.ui.component.MainIcon
import com.example.soundquest2.ui.intent.GameIntent
import com.example.soundquest2.ui.state.GameUiState
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.theme.LocalAppImages

@Composable
fun RoundScreen(
    state: GameUiState.Round,
    onIntent: (GameIntent) -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        MainBackground(LocalAppImages.current.bg)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(
                        R.string.round_progress,
                        state.roundNumber,
                        state.totalRounds
                    ),
                    style = AppTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                MainIcon(
                    R.drawable.again_icon,
                    onClick = {
                        onIntent(GameIntent.RestartVerse)
                    }
                )

                state.options.forEach { content ->
                    MainButton(
                        text = content.title,
                        modifier = Modifier.height(60.dp),
                        onClick = {
                            onIntent(GameIntent.ChooseAnswer(content))
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 55.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MainIcon(R.drawable.hint_50_50) {
//                        onIntent(RoundIntent.HintClicked(HintType.FiftyFifty))
                    }

                    MainIcon(R.drawable.hint_author) {
//                        onIntent(RoundIntent.HintClicked(HintType.AuthorOrStudio))
                    }

                    MainIcon(R.drawable.hint_random) {
//                        onIntent(RoundIntent.HintClicked(HintType.Random))
                    }
                }
            }
        }
    }
}