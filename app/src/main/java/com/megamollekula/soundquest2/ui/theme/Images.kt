package com.megamollekula.soundquest2.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.megamollekula.soundquest2.R

data class AppImages(
    val bg: Int,
)

val LightImages = AppImages(
    bg = R.drawable.bg_light,
)

val DarkImages = AppImages(
    bg = R.drawable.bg_dark,
)

val LocalAppImages = staticCompositionLocalOf<AppImages> {
    error("AppImages not provided")
}
