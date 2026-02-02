package com.example.soundquest2.ui.component.animation.button

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun AnimatedPressInteraction(isPressed: Boolean): Float {
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.4f else 1f,
        animationSpec = tween(120),
        label = "press_alpha"
    )
    return alpha
}
