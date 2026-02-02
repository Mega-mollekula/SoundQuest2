package com.example.soundquest2.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soundquest2.ui.component.animation.button.AnimatedButtonGradient
import com.example.soundquest2.ui.component.animation.button.AnimatedPressInteraction
import com.example.soundquest2.ui.theme.style.ButtonStyle
import com.example.soundquest2.ui.theme.style.ThemeTextStyle

@Composable
fun MainButton(
    label: String,
    buttonStyle: ButtonStyle,
    textStyle: ThemeTextStyle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val backgroundGradient = AnimatedButtonGradient(
        topColor = buttonStyle.topBackgroundColor,
        bottomColor = buttonStyle.bottomBackgroundColor
    )


    val borderGradient = Brush.verticalGradient(
        colors = listOf(
            buttonStyle.topBorderColor,
            buttonStyle.bottomBorderColor
        )
    )

    val shape = RoundedCornerShape(36.dp)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressAlpha = AnimatedPressInteraction(isPressed)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
            .clip(shape)
            .background(borderGradient)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .clip(shape)
                .alpha(pressAlpha)
                .background(backgroundGradient),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = label,
                    style = textStyle.labelSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}