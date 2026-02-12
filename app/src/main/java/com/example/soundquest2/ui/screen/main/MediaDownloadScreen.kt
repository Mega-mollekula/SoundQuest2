package com.example.soundquest2.ui.screen.main

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.ui.component.MainButton
import com.example.soundquest2.ui.intent.MediaDownloadIntent
import com.example.soundquest2.ui.model.FactsCatalog
import com.example.soundquest2.ui.state.DownloadUiState
import com.example.soundquest2.ui.theme.AppTheme
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.toUiError
import kotlinx.coroutines.delay

@Composable
fun MediaDownloadScreen(
    uiState: DownloadUiState,
    toGameScreen: () -> Unit,
    count: Int,
    language: String,
    gameMode: GameMode,
    onDownloadIntent: (MediaDownloadIntent) -> Unit,
    onExit: () -> Unit
) {
    val facts = FactsCatalog.facts
    var currentFactIndex by remember { mutableIntStateOf(0) }
    val alpha = remember { Animatable(1f) }

    // Cycle facts every 7 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(7_000)

            // Fade out
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 800)
            )

            // Change fact
            currentFactIndex = (currentFactIndex + 1) % facts.size

            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 800)
            )
        }
    }

    LaunchedEffect(Unit) {
        onDownloadIntent(
            MediaDownloadIntent.StartDownload(
                language = language,
                count = count,
                gameMode = gameMode
            )
        )
    }

    val fact = facts[currentFactIndex]

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = fact.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { this.alpha = alpha.value }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.9f),
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 24.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 32.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (uiState) {

                    is DownloadUiState.Completed -> {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text =  stringResource(R.string.completed) + "\n\n" +
                                        stringResource(R.string.downloaded_count, uiState.completed),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = AppTypography.labelMedium,
                                color = Color.Green
                            )

                            Text(
                                text = stringResource(R.string.skipped_count, uiState.skipped),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = AppTypography.labelMedium,
                                color = Color.Gray
                            )

                            Text(
                                text = stringResource(R.string.failed_count, uiState.failed),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = AppTypography.labelMedium,
                                color = Color.Red
                            )
                        }
                    }

                    is DownloadUiState.Preparing -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(100.dp),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.3f),
                            strokeWidth = 10.dp
                        )
                    }

                    is DownloadUiState.Downloading -> {

                        val progress = if (uiState.total > 0) {
                            (uiState.completed.toFloat() + uiState.skipped.toFloat() + uiState.failed) / uiState.total
                        }
                        else 0f

                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = 0.3f)
                        )

                        Text(
                            text = "${uiState.completed + uiState.skipped + uiState.failed} / ${uiState.total}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = AppTypography.labelSmall,
                            color = Color.White
                        )
                    }

                    is DownloadUiState.Error -> {
                        Image(
                            painter = painterResource(id = uiState.error.toUiError().iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(180.dp)
                        )

                        Text(
                            text = stringResource(uiState.error.toUiError().titleRes),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = AppTypography.titleLarge,
                            color = Color.Red
                        )

                        Text(
                            text = stringResource(uiState.error.toUiError().messageRes),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = AppTypography.titleMedium,
                            color = Color.Red
                        )
                    }

                    is DownloadUiState.Idle -> {
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }

            when (uiState) {
                is DownloadUiState.Completed -> {
                    MainButton(
                        onClick = { toGameScreen() },
                        text = stringResource(R.string.start)
                    )
                }

                is DownloadUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        MainButton(
                            onClick = {
                                onDownloadIntent(
                                    MediaDownloadIntent.Retry(
                                        uiState.gameMode,
                                        uiState.language,
                                        uiState.count
                                    )
                                )
                            },
                            text = stringResource(R.string.try_again_button)
                        )
                        MainButton(
                            onClick = { onExit() },
                            text = stringResource(R.string.exit)
                        )
                    }
                }

                else -> {
                    Column() {
                        Text(
                            text = stringResource(id = fact.descriptionRes),
                            style = AppTypography.labelSmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Downloading",
    showBackground = true,
    locale = "en"
)
@Composable
fun MediaDownloadScreenDownloadingPreview() {
    AppTheme(darkTheme = true) {
        MediaDownloadScreen(
            onDownloadIntent = {},
            onExit = {},
            toGameScreen = {},
            uiState = DownloadUiState.Completed(
                3,
                10,
                3,
                2
            ),
            count = 10,
            language = "ru",
            gameMode = GameMode.FastStart
        )
    }
}

@Preview(
    name = "Downloading",
    showBackground = true,
    locale = "ru"
)
@Composable
fun MediaDownloadScreenDownloadingPreviewRu() {
    AppTheme(darkTheme = true) {
        MediaDownloadScreen(
            toGameScreen = {},
            onDownloadIntent = {},
            onExit = {},
            uiState = DownloadUiState.Completed(
                3,
                10,
                3,
                2
            ),
            count = 10,
            language = "ru",
            gameMode = GameMode.FastStart
        )
    }
}

