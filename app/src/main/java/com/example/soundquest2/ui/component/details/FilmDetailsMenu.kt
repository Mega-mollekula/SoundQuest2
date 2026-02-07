package com.example.soundquest2.ui.component.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundquest2.R
import com.example.soundquest2.ui.model.UiFilm
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.util.localizedName

@Composable
fun FilmDetailsMenu(
    film: UiFilm
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = film.title,
            style = AppTypography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${film.releaseYear} • ${film.filmType.localizedName()} • ${film.durationMinutes} min",
            style = AppTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = film.description,
            style = AppTypography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        MediaDetailRow(
            label = stringResource(R.string.label_director),
            value = film.director
        )
        MediaDetailRow(
            label = stringResource(R.string.label_stars),
            value = film.stars
        )
        MediaDetailRow(
            label = stringResource(R.string.label_rating),
            value = stringResource(R.string.label_imdb_rating, film.imdbRating)
        )
    }
}
