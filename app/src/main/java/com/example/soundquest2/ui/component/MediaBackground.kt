package com.example.soundquest2.ui.component

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.soundquest2.core.media.VideoPlayer
import com.example.soundquest2.ui.state.GameUiState

@OptIn(UnstableApi::class)
@Composable
fun VideoBackground(
    videoPlayer: VideoPlayer,
    state: GameUiState.Result,
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0x19000000),
            Color(0xE6000000)
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {


        if (!state.correct.localVideoPath.isNullOrEmpty()) {

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    PlayerView(context).apply {
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        player = videoPlayer.exoPlayer
                    }
                }
            )
        }

        else {
            AsyncImage(
                model = state.correct.pictureUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                placeholder = ColorPainter(Color.Black),
                error = ColorPainter(Color.Black)
            )
        }

        Spacer(modifier = Modifier.fillMaxSize().background(gradient))
    }
}