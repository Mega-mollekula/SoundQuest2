package com.example.soundquest2.ui.component.title

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundquest2.ui.component.Inter

@Composable
fun LargeTitle(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 80.sp,
    textColor: Color = Color.DarkGray,
    shadowColor: Color = Color.Black.copy(alpha = 0.4f)
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = fontSize * 0.95f,
            color = shadowColor,
            modifier = Modifier
                .offset(x = 2.dp, y = 2.dp)
        )

        Text(
            text = text,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            fontFamily = Inter,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = fontSize * 0.95f,
            color = textColor
        )
    }
}