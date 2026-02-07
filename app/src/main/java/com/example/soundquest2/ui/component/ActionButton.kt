package com.example.soundquest2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.theme.AppTheme

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    onExit: () -> Unit
) {

    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .size(width = 90.dp, height = 45.dp)
            .clip(shape)
            .clickable(onClick = onExit)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.exit),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(
    showBackground = true,
    locale = "ru"
)
@Composable
fun BackButtonPreview() {
    AppTheme(false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            ActionButton() {}
        }
    }
}