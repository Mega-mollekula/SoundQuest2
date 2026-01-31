package com.example.soundquest2.ui.theme.style

import androidx.compose.ui.graphics.Color

data class BgStyle(
    val gradientColors: List<Color>? = null,
    val topColor: Color? = null,
    val texture: Int? = null // id of resource
) {

    fun hasGradient(): Boolean {
        return gradientColors != null && topColor != null
    }

    fun hasTexture(): Boolean {
        return texture != null
    }
}