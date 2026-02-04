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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.unit.sp
import com.example.soundquest2.R
import com.example.soundquest2.ui.model.FactsCatalog
import com.example.soundquest2.ui.state.DownloadUiState
import com.example.soundquest2.ui.theme.style.appTextStyleBuilder
import com.example.soundquest2.ui.util.toUiError
import kotlinx.coroutines.delay

@Composable
fun MediaDownloadScreen(
    uiState: DownloadUiState,
    onCompleted: () -> Unit,
    onRetry: () -> Unit,
    onExit: () -> Unit
) {
    val facts = FactsCatalog.facts
    var currentFactIndex by remember { mutableStateOf(0) }
    val alpha = remember { Animatable(1f) }

    // Cycle facts every 7 seconds with fade-out / fade-in
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

    val fact = facts[currentFactIndex]

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background image
        Image(
            painter = painterResource(id = fact.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { this.alpha = alpha.value }
        )

        // Gradient overlay
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
                                text =  stringResource(R.string.completed) + "\n" +
                                        stringResource(R.string.downloaded_count, uiState.completed),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = appTextStyleBuilder(
                                    Color.Green,
                                    25.sp,
                                    lineHeight = 33.sp,
                                    shadowColor = Color.Black
                                )
                            )

                            Text(
                                text = stringResource(R.string.skipped_count, uiState.skipped),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = appTextStyleBuilder(Color.Gray, 25.sp, shadowColor = Color.Black)
                            )

                            Text(
                                text = stringResource(R.string.failed_count, uiState.failed),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = appTextStyleBuilder(Color.Red, 25.sp, shadowColor = Color.Black)
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
                            (uiState.completed.toFloat() + uiState.skipped.toFloat()) / uiState.total
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
                            text = "${uiState.completed + uiState.skipped} / ${uiState.total}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = appTextStyleBuilder(Color.White, 20.sp, shadowColor = Color.Black)
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
                            style = appTextStyleBuilder(Color.Red, 50.sp, shadowColor = Color.Black)
                        )

                        Text(
                            text = stringResource(uiState.error.toUiError().messageRes),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = appTextStyleBuilder(Color.Red, 30.sp, shadowColor = Color.Black)
                        )
                    }

                    is DownloadUiState.Idle -> {
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }

            when (uiState) {
                is DownloadUiState.Completed -> {
                    DownloadButton(
                        onCompleted,
                        stringResource(R.string.continue_button)
                    )
                }

                is DownloadUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        DownloadButton(
                            onRetry,
                            stringResource(R.string.try_again_button)
                        )
                        DownloadButton(
                            onExit,
                            stringResource(R.string.exit_button)
                        )
                    }
                }

                else -> {
                    Column() {
                        Text(
                            text = stringResource(id = fact.descriptionRes),
                            style = appTextStyleBuilder(Color.White, 20.sp, 28.sp, shadowColor = Color.Black)
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
)
@Composable
fun MediaDownloadScreenDownloadingPreview() {
    MediaDownloadScreen(
        onRetry = {},
        onExit = {},
        onCompleted = {},
        uiState = DownloadUiState.Preparing
    )
}

@Composable
fun DownloadButton(
    onClick: () -> Unit,
    label: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
    ) {
        Text(
            text = label,
            style = appTextStyleBuilder(
                color = Color.Black,
                fontSize = 20.sp,
            )
        )
    }
}

