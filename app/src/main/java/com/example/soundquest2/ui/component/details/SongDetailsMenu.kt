package com.example.soundquest2.ui.component.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundquest2.R
import com.example.soundquest2.ui.model.UiSong
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.util.localizedName

@Composable
fun SongDetailsMenu(
    song: UiSong
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = song.title,
            style = AppTypography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${song.artist.name} • ${song.genre.localizedName()} • ${song.era.localizedName()}",
            style = AppTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = song.info,
            style = AppTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        MediaDetailRow(
            label = stringResource(R.string.label_artist),
            value = song.artist.name
        )
        MediaDetailRow(
            label = stringResource(R.string.label_genre),
            value = song.genre.localizedName()
        )
        MediaDetailRow(
            label = stringResource(R.string.label_era),
            value = song.era.localizedName()
        )
    }
}
