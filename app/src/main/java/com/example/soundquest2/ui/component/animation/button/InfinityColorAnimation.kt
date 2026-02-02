package com.example.soundquest2.ui.component.animation.button

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun InfinityColorAnimation(
    from: Color,
    to: Color,
    durationMillis: Int = 1000
): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "infinityAnimation")

    return infiniteTransition.animateColor(
        initialValue = from,
        targetValue = to,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "infinityAnimation"
    ).value
}