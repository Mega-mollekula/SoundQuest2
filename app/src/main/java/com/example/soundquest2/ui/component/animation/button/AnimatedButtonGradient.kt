package com.example.soundquest2.ui.component.animation.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.soundquest2.ui.util.makeWarmer

@Composable
fun AnimatedButtonGradient(topColor: Color, bottomColor: Color): Brush {
    val animatedTop = InfinityColorAnimation(
        from = topColor,
        to = topColor.makeWarmer(0.2f)
    )

    return Brush.verticalGradient(
        colors = listOf(
            animatedTop,
            bottomColor
        )
    )
}