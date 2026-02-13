package com.example.soundquest2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onAction: () -> Unit
) {

    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .size(width = 100.dp, height = 45.dp)
            .clip(shape)
            .clickable(onClick = onAction)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}