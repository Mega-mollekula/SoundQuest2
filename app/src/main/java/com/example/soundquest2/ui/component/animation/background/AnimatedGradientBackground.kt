package com.example.soundquest2.ui.component.animation.background

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.soundquest2.ui.theme.style.BgStyle

@Composable
fun AnimatedGradientBackground(
    style: BgStyle,
    animationDelay: Int = 3000
): Brush {
    val gradientColors = style.gradientColors ?: emptyList()
    val topColor = style.topColor ?: Color(0xFF030e1f)

    val color = remember(style) { Animatable(gradientColors.first()) }
    var index by remember(style) { mutableIntStateOf(0) }

    LaunchedEffect(style) {
        while (true) {
            val nextIndex = (index + 1) % gradientColors.size
            color.animateTo(
                targetValue = gradientColors[nextIndex],
                animationSpec = tween(animationDelay, easing = LinearEasing)
            )
            index = nextIndex
        }
    }

    return Brush.verticalGradient(
        colors = listOf(topColor, color.value)
    )
}