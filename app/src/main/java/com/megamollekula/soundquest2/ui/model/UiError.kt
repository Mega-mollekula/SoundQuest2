package com.megamollekula.soundquest2.ui.model

data class UiError(
    val titleRes: Int,
    val messageRes: Int,
    val iconRes: Int,
    val canRetry: Boolean
)