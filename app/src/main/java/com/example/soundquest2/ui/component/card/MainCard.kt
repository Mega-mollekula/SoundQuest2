package com.example.soundquest2.ui.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soundquest2.ui.theme.style.MainCardStyle

@Composable
fun MainCard(
    modifier: Modifier = Modifier,
    mainCardStyle: MainCardStyle,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(
        modifier = modifier.shadow(16.dp, RoundedCornerShape(60.dp)),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            listOf(mainCardStyle.topColor, mainCardStyle.bottomColor)
                        ),
                        alpha = mainCardStyle.alpha,
                        cornerRadius = CornerRadius(28.dp.toPx())
                    )
                }
                .padding(6.dp),
            content = content
        )
    }
}