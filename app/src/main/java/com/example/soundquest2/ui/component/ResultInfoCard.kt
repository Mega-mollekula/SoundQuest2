package com.example.soundquest2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.theme.AppTypography

@Composable
fun ResultInfoCard(
    isCorrect: Boolean,
    title: String,
    onFavouriteClick: () -> Unit,
    onExpandClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(10.dp)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isCorrect) {
                stringResource(R.string.result_correct)
            } else {
                stringResource(R.string.result_incorrect)
            },
            color = MaterialTheme.colorScheme.onPrimary,
            style = AppTypography.titleMedium
        )

        Box(
            modifier = modifier
                .width(332.dp)
                .height(60.dp)
                .clip(shape)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                )
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = shape
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = AppTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = onFavouriteClick) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Add to favourites",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    IconButton(onClick = onExpandClick) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Expand details",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}