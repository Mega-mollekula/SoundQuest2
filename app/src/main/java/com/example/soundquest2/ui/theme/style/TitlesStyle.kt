package com.example.soundquest2.ui.theme.style

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.soundquest2.ui.component.Inter

fun appTextStyleBuilder(
    color: Color,
    fontSize: TextUnit,
    lineHeight: TextUnit = fontSize,
    fontWeight: FontWeight = FontWeight.Bold,
    shadowColor: Color? = null
) =  TextStyle (
    fontFamily = Inter,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = 0.sp,
    color = color,
    shadow = shadowColor?.let {
        Shadow(
            it,
            offset = Offset(0f, 2f),
            blurRadius = 8f
        )
    }
)

fun titleLarge (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 58.sp,
    lineHeight = 58.sp,
    letterSpacing = 0.sp,
    color = color,
    shadow = Shadow(
        color = Color.Black.copy(alpha = 0.5f),
        offset = Offset(2f, 2f),
        blurRadius = 4f
    )
)

fun titleMedium (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 42.sp,
    lineHeight = 42.sp,
    letterSpacing = 0.sp,
    color = color
)

fun titleSmall (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 24.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
    color = color
)

fun labelSmall (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 18.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.5.sp,
    color = color
)

fun labelMedium (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 23.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.6.sp,
    color = color
)

fun labelLarge (color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 28.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.7.sp,
    color = color
)

fun bodySmall(color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.2.sp,
    color = color,
    textAlign = TextAlign.Justify
)

fun bodyMedium(color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.2.sp,
    color = color,
    textAlign = TextAlign.Justify
)

fun bodyLarge(color: Color): TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.2.sp,
    color = color,
    textAlign = TextAlign.Justify
)