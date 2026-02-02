package com.example.soundquest2.ui.component.bg

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.soundquest2.ui.component.animation.background.AnimatedGradientBackground
import com.example.soundquest2.ui.theme.style.BgStyle

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    style: BgStyle,
    animationDelay: Int = 2000,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            style.hasTexture() -> {
                Image(
                    painter = painterResource(id = style.texture!!),
                    contentDescription = "Background texture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            style.hasGradient() -> {
                val brush = AnimatedGradientBackground(
                    style = style,
                    animationDelay = animationDelay
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF121212))
                )
            }
        }
        content()
    }
}
