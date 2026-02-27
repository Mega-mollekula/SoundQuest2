package com.megamollekula.soundquest2.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.megamollekula.soundquest2.ui.theme.AppTypography

@Composable
fun ResultRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = AppTypography.labelSmall,
            color = Color.White.copy(alpha = 0.7f)
        )

        Text(
            text = value,
            style = AppTypography.labelSmall,
            color = Color.White
        )
    }
}